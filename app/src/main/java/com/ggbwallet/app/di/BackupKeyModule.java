package com.ggbwallet.app.di;

import dagger.Module;
import dagger.Provides;

import com.ggbwallet.app.interact.ExportWalletInteract;
import com.ggbwallet.app.interact.FetchWalletsInteract;
import com.ggbwallet.app.repository.WalletRepositoryType;
import com.ggbwallet.app.service.KeyService;
import com.ggbwallet.app.viewmodel.BackupKeyViewModelFactory;

@Module
public class BackupKeyModule {
    @Provides
    BackupKeyViewModelFactory provideBackupKeyViewModelFactory(
            KeyService keyService,
            ExportWalletInteract exportWalletInteract,
            FetchWalletsInteract fetchWalletsInteract) {
        return new BackupKeyViewModelFactory(
                keyService,
                exportWalletInteract,
                fetchWalletsInteract);
    }

    @Provides
    ExportWalletInteract provideExportWalletInteract(
            WalletRepositoryType walletRepository) {
        return new ExportWalletInteract(walletRepository);
    }

    @Provides
    FetchWalletsInteract provideFetchAccountsInteract(WalletRepositoryType accountRepository) {
        return new FetchWalletsInteract(accountRepository);
    }
}