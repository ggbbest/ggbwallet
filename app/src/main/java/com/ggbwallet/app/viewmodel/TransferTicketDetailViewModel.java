package com.ggbwallet.app.viewmodel;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ggbwallet.app.C;
import com.ggbwallet.app.entity.AnalyticsProperties;
import com.ggbwallet.app.entity.ContractType;
import com.ggbwallet.app.entity.CryptoFunctions;
import com.ggbwallet.app.entity.Operation;
import com.ggbwallet.app.entity.SignAuthenticationCallback;
import com.ggbwallet.app.entity.TransactionData;
import com.ggbwallet.app.entity.Wallet;
import com.ggbwallet.app.entity.cryptokeys.SignatureFromKey;
import com.ggbwallet.app.entity.tokens.Token;
import com.ggbwallet.app.interact.CreateTransactionInteract;
import com.ggbwallet.app.interact.FetchTransactionsInteract;
import com.ggbwallet.app.interact.GenericWalletInteract;
import com.ggbwallet.app.repository.EthereumNetworkRepository;
import com.ggbwallet.app.repository.TokenRepository;
import com.ggbwallet.app.service.AnalyticsServiceType;
import com.ggbwallet.app.service.AssetDefinitionService;
import com.ggbwallet.app.service.GasService;
import com.ggbwallet.app.service.KeyService;
import com.ggbwallet.app.service.TokensService;
import com.ggbwallet.app.util.Utils;
import com.ggbwallet.app.web3.entity.Web3Transaction;
import com.ggbwallet.token.entity.SalesOrderMalformed;
import com.ggbwallet.token.entity.SignableBytes;
import com.ggbwallet.token.tools.ParseMagicLink;

import org.web3j.protocol.core.methods.response.EthEstimateGas;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by James on 21/02/2018.
 */
public class TransferTicketDetailViewModel extends BaseViewModel {
    private final MutableLiveData<Wallet> defaultWallet = new MutableLiveData<>();
    private final MutableLiveData<String> newTransaction = new MutableLiveData<>();
    private final MutableLiveData<String> universalLinkReady = new MutableLiveData<>();
    private final MutableLiveData<String> userTransaction = new MutableLiveData<>();
    private final MutableLiveData<TransactionData> transactionFinalised = new MutableLiveData<>();
    private final MutableLiveData<Throwable> transactionError = new MutableLiveData<>();

    private final GenericWalletInteract genericWalletInteract;
    private final KeyService keyService;
    private final CreateTransactionInteract createTransactionInteract;
    private final FetchTransactionsInteract fetchTransactionsInteract;
    private final AssetDefinitionService assetDefinitionService;
    private final GasService gasService;
    private final AnalyticsServiceType analyticsService;
    private final TokensService tokensService;

    private ParseMagicLink parser;
    private Token token;

    private byte[] linkMessage;

    TransferTicketDetailViewModel(GenericWalletInteract genericWalletInteract,
                                  KeyService keyService,
                                  CreateTransactionInteract createTransactionInteract,
                                  FetchTransactionsInteract fetchTransactionsInteract,
                                  AssetDefinitionService assetDefinitionService,
                                  GasService gasService,
                                  AnalyticsServiceType analyticsService,
                                  TokensService tokensService) {
        this.genericWalletInteract = genericWalletInteract;
        this.keyService = keyService;
        this.createTransactionInteract = createTransactionInteract;
        this.fetchTransactionsInteract = fetchTransactionsInteract;
        this.assetDefinitionService = assetDefinitionService;
        this.gasService = gasService;
        this.analyticsService = analyticsService;
        this.tokensService = tokensService;
    }


    public MutableLiveData<TransactionData> transactionFinalised()
    {
        return transactionFinalised;
    }
    public MutableLiveData<Throwable> transactionError() { return transactionError; }

    public LiveData<Wallet> defaultWallet()
    {
        return defaultWallet;
    }
    public LiveData<String> newTransaction() { return newTransaction; }
    public LiveData<String> universalLinkReady() { return universalLinkReady; }
    public LiveData<String> userTransaction() { return userTransaction; }
    private void initParser()
    {
        if (parser == null)
        {
            parser = new ParseMagicLink(new CryptoFunctions(), EthereumNetworkRepository.extraChains());
        }
    }

    public void prepare(Token token)
    {
        this.token = token;
        disposable = genericWalletInteract
                .find()
                .subscribe(this::onDefaultWallet, this::onError);

        gasService.startGasPriceCycle(token.tokenInfo.chainId);
    }

    private void onDefaultWallet(Wallet wallet) {
        defaultWallet.setValue(wallet);
    }

    public Wallet getWallet()
    {
       return defaultWallet.getValue();
    }

    public void setWallet(Wallet wallet)
    {
        defaultWallet.setValue(wallet);
    }

    private void onCreateTransaction(String transaction)
    {
        userTransaction.postValue(transaction);
    }

    public void generateUniversalLink(List<BigInteger> ticketSendIndexList, String contractAddress, long expiry)
    {
        initParser();
        if (ticketSendIndexList == null || ticketSendIndexList.size() == 0)
            return; //TODO: Display error message

        int[] indexList = Utils.bigIntegerListToIntList(ticketSendIndexList);

        //NB tradeBytes is the exact bytes the ERC875 contract builds to check the valid order.
        //This is what we must sign.
        SignableBytes tradeBytes = new SignableBytes(parser.getTradeBytes(indexList, contractAddress, BigInteger.ZERO, expiry));
        try
        {
            linkMessage = ParseMagicLink.generateLeadingLinkBytes(indexList, contractAddress, BigInteger.ZERO, expiry);
        }
        catch (SalesOrderMalformed e)
        {
            //TODO: Display appropriate error to user
        }

        //sign this link
        disposable = createTransactionInteract
                .sign(defaultWallet().getValue(), tradeBytes, token.tokenInfo.chainId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::gotSignature, this::onError);
    }

    public void generateSpawnLink(List<BigInteger> tokenIds, String contractAddress, long expiry)
    {
        initParser();
        SignableBytes tradeBytes = new SignableBytes(parser.getSpawnableBytes(tokenIds, contractAddress, BigInteger.ZERO, expiry));
        try
        {
            linkMessage = ParseMagicLink.generateSpawnableLeadingLinkBytes(tokenIds, contractAddress, BigInteger.ZERO, expiry);
        }
        catch (SalesOrderMalformed e)
        {
            //TODO: Display appropriate error to user
        }

        //sign this link
        disposable = createTransactionInteract
                .sign(defaultWallet().getValue(), tradeBytes, token.tokenInfo.chainId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::gotSignature, this::onError);
    }

    private void gotSignature(SignatureFromKey signature)
    {
        String universalLink = parser.completeUniversalLink(token.tokenInfo.chainId, linkMessage, signature.signature);
        //Now open the share icon
        universalLinkReady.postValue(universalLink);
    }

    public void createTicketTransfer(String to, Token token, List<BigInteger> transferList)
    {
        if (!token.contractTypeValid())
        {
            //need to determine the spec
            disposable = fetchTransactionsInteract.queryInterfaceSpec(token.tokenInfo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(spec -> onInterfaceSpec(spec, to, token, transferList), this::onError);
        }
    }

    private void onInterfaceSpec(ContractType spec, String to, Token token, List<BigInteger> transferList)
    {
        token.setInterfaceSpec(spec);
        TokensService.setInterfaceSpec(token.tokenInfo.chainId, token.getAddress(), spec);
        createTicketTransfer(to, token, transferList);
    }

    public AssetDefinitionService getAssetDefinitionService()
    {
        return assetDefinitionService;
    }

    public void stopGasSettingsFetch()
    {
        gasService.stopGasPriceCycle();
    }

    public void getAuthorisation(Activity activity, SignAuthenticationCallback callback)
    {
        if (defaultWallet.getValue() != null)
        {
            keyService.getAuthenticationForSignature(defaultWallet.getValue(), activity, callback);
        }
    }

    public void resetSignDialog()
    {
        keyService.resetSigningDialog();
    }

    public void completeAuthentication(Operation signData)
    {
        keyService.completeAuthentication(signData);
    }

    public Single<EthEstimateGas> calculateGasEstimate(Wallet wallet, byte[] transactionBytes, int chainId, String sendAddress, BigDecimal sendAmount)
    {
        return gasService.calculateGasEstimate(transactionBytes, chainId, sendAddress, sendAmount.toBigInteger(), wallet);
    }

    public void getAuthentication(Activity activity, Wallet wallet, SignAuthenticationCallback callback)
    {
        keyService.getAuthenticationForSignature(wallet, activity, callback);
    }

    public void failedAuthentication(Operation signData)
    {
        keyService.completeAuthentication(signData);
    }

    public void sendTransaction(Web3Transaction finalTx, Wallet wallet, int chainId)
    {
        disposable = createTransactionInteract
                .createWithSig(wallet, finalTx, chainId)
                .subscribe(transactionFinalised::postValue,
                        transactionError::postValue);
    }

    public void createERC721Transfer(String to, String contractAddress, String tokenId, BigInteger gasPrice, BigInteger gasLimit, int chainId)
    {
        final byte[] data = getERC721TransferBytes(to, contractAddress, tokenId, chainId);
        disposable = createTransactionInteract
                .create(defaultWallet.getValue(), contractAddress, BigInteger.valueOf(0), gasPrice, gasLimit, data, chainId)
                .subscribe(this::onCreateTransaction, this::onError);
    }

    public byte[] getERC721TransferBytes(String to, String contractAddress, String tokenId, int chainId) {
        Token token = tokensService.getToken(chainId, contractAddress);
        List<BigInteger> tokenIds = token.stringHexToBigIntegerList(tokenId);
        return TokenRepository.createERC721TransferFunction(to, token, tokenIds);
    }

    public void actionSheetConfirm(String mode)
    {
        AnalyticsProperties analyticsProperties = new AnalyticsProperties();
        analyticsProperties.setData(mode);

        analyticsService.track(C.AN_CALL_ACTIONSHEET, analyticsProperties);
    }


    public TokensService getTokenService() {
        return tokensService;
    }
}
