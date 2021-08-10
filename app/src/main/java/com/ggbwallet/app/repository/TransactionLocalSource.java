package com.ggbwallet.app.repository;

import com.ggbwallet.app.entity.ActivityMeta;
import com.ggbwallet.app.entity.Transaction;
import com.ggbwallet.app.entity.Wallet;
import com.ggbwallet.app.repository.entity.RealmAuxData;

import org.web3j.protocol.core.methods.response.EthTransaction;

import java.util.List;

import io.reactivex.Single;
import io.realm.Realm;

public interface TransactionLocalSource {
	Single<ActivityMeta[]> fetchActivityMetas(Wallet wallet, List<Integer> networkFilters, long fetchTime, int fetchLimit);
	Transaction fetchTransaction(Wallet wallet, String hash);
	void putTransaction(Wallet wallet, Transaction tx);
    void deleteTransaction(Wallet wallet, String oldTxHash);

	Realm getRealmInstance(Wallet wallet);
	Single<ActivityMeta[]> fetchActivityMetas(Wallet wallet, int chainId, String tokenAddress, int historyCount);
	Single<ActivityMeta[]> fetchEventMetas(Wallet wallet, List<Integer> networkFilters);
	void markTransactionBlock(String walletAddress, String hash, long blockValue);
	Transaction[] fetchPendingTransactions(String currentAddress);

	RealmAuxData fetchEvent(String walletAddress, String eventKey);

	Transaction storeRawTx(Wallet wallet, int chainId, EthTransaction object, long timeStamp, boolean isSuccessful);

    long fetchTxCompletionTime(Wallet wallet, String hash);

    Single<Boolean> deleteAllForWallet(String currentAddress);
}
