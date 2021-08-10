package com.ggbwallet.app.di;

import com.ggbwallet.app.interact.GenericWalletInteract;
import com.ggbwallet.app.repository.PreferenceRepositoryType;
import com.ggbwallet.app.repository.WalletRepositoryType;
import com.ggbwallet.app.router.ManageWalletsRouter;
import com.ggbwallet.app.router.MyAddressRouter;
import com.ggbwallet.app.viewmodel.NewSettingsViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class NewSettingsModule {
    @Provides
    NewSettingsViewModelFactory provideNewSettingsViewModelFactory(
            GenericWalletInteract genericWalletInteract,
            MyAddressRouter myAddressRouter,
            ManageWalletsRouter manageWalletsRouter,
            PreferenceRepositoryType preferenceRepository
    ) {
        return new NewSettingsViewModelFactory(
                genericWalletInteract,
                myAddressRouter,
                manageWalletsRouter,
                preferenceRepository);
    }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    MyAddressRouter provideMyAddressRouter() {
        return new MyAddressRouter();
    }

    @Provides
    ManageWalletsRouter provideManageWalletsRouter() {
        return new ManageWalletsRouter();
    }
}
