package com.ggbwallet.app.di;

import com.ggbwallet.app.interact.ImportWalletInteract;
import com.ggbwallet.app.repository.WalletRepositoryType;
import com.ggbwallet.app.service.AnalyticsServiceType;
import com.ggbwallet.app.service.KeyService;
import com.ggbwallet.app.viewmodel.ImportWalletViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class ImportModule {
    @Provides
    ImportWalletViewModelFactory provideImportWalletViewModelFactory(
            ImportWalletInteract importWalletInteract,
            KeyService keyService,
            AnalyticsServiceType analyticsService) {
        return new ImportWalletViewModelFactory(importWalletInteract, keyService, analyticsService);
    }

    @Provides
    ImportWalletInteract provideImportWalletInteract(
            WalletRepositoryType walletRepository) {
        return new ImportWalletInteract(walletRepository);
    }
}
