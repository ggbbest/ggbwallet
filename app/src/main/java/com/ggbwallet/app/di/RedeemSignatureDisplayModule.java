package com.ggbwallet.app.di;

import com.ggbwallet.app.interact.CreateTransactionInteract;
import com.ggbwallet.app.interact.FetchTokensInteract;
import com.ggbwallet.app.interact.GenericWalletInteract;
import com.ggbwallet.app.interact.MemPoolInteract;
import com.ggbwallet.app.interact.SignatureGenerateInteract;
import com.ggbwallet.app.repository.TokenRepositoryType;
import com.ggbwallet.app.repository.TransactionRepositoryType;
import com.ggbwallet.app.repository.WalletRepositoryType;
import com.ggbwallet.app.router.AssetDisplayRouter;
import com.ggbwallet.app.service.AssetDefinitionService;
import com.ggbwallet.app.service.KeyService;
import com.ggbwallet.app.viewmodel.RedeemSignatureDisplayModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by James on 25/01/2018.
 */

@Module
public class RedeemSignatureDisplayModule {
    @Provides
    RedeemSignatureDisplayModelFactory signatureDisplayModelFactory(
            GenericWalletInteract genericWalletInteract,
            SignatureGenerateInteract signatureGenerateInteract,
            CreateTransactionInteract createTransactionInteract,
            KeyService keyService,
            FetchTokensInteract fetchTokensInteract,
            MemPoolInteract memPoolInteract,
            AssetDisplayRouter assetDisplayRouter,
            AssetDefinitionService assetDefinitionService) {
        return new RedeemSignatureDisplayModelFactory(
                genericWalletInteract, signatureGenerateInteract, createTransactionInteract, keyService, fetchTokensInteract, memPoolInteract, assetDisplayRouter, assetDefinitionService);
    }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    FetchTokensInteract provideFetchTokensInteract(TokenRepositoryType tokenRepository) {
        return new FetchTokensInteract(tokenRepository);
    }

    @Provides
    SignatureGenerateInteract provideSignatureGenerateInteract(WalletRepositoryType walletRepository) {
        return new SignatureGenerateInteract(walletRepository);
    }

    @Provides
    CreateTransactionInteract provideCreateTransactionInteract(TransactionRepositoryType transactionRepository) {
        return new CreateTransactionInteract(transactionRepository);
    }

    @Provides
    MemPoolInteract provideMemPoolInteract(TokenRepositoryType tokenRepository) {
        return new MemPoolInteract(tokenRepository);
    }

    @Provides
    AssetDisplayRouter provideAssetDisplayRouter() {
        return new AssetDisplayRouter();
    }
}
