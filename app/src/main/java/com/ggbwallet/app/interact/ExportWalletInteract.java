package com.ggbwallet.app.interact;

import android.util.Log;

import com.ggbwallet.app.BuildConfig;
import com.ggbwallet.app.repository.WalletRepositoryType;
import com.ggbwallet.app.entity.Wallet;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ExportWalletInteract {

    private final WalletRepositoryType walletRepository;

    public ExportWalletInteract(WalletRepositoryType walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Single<String> export(Wallet wallet, String keystorePassword, String backupPassword) {
        if (BuildConfig.DEBUG) Log.d("RealmDebug", "export + " + wallet.address);
        return walletRepository
                    .exportWallet(wallet, keystorePassword, backupPassword)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
