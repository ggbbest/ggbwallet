package com.ggbwallet.app.di;


import com.ggbwallet.app.interact.ChangeTokenEnableInteract;
import com.ggbwallet.app.interact.FetchTokensInteract;
import com.ggbwallet.app.interact.GenericWalletInteract;
import com.ggbwallet.app.repository.TokenRepositoryType;
import com.ggbwallet.app.repository.WalletRepositoryType;
import com.ggbwallet.app.router.AssetDisplayRouter;
import com.ggbwallet.app.router.Erc20DetailRouter;
import com.ggbwallet.app.router.MyAddressRouter;
import com.ggbwallet.app.service.AssetDefinitionService;
import com.ggbwallet.app.service.TokensService;
import com.ggbwallet.app.viewmodel.WalletViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class WalletModule {
    @Provides
    WalletViewModelFactory provideWalletViewModelFactory(
            FetchTokensInteract fetchTokensInteract,
            Erc20DetailRouter erc20DetailRouter,
            AssetDisplayRouter assetDisplayRouter,
            GenericWalletInteract genericWalletInteract,
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService,
            ChangeTokenEnableInteract changeTokenEnableInteract,
            MyAddressRouter myAddressRouter) {
        return new WalletViewModelFactory(
                fetchTokensInteract,
                erc20DetailRouter,
                assetDisplayRouter,
                genericWalletInteract,
                assetDefinitionService,
                tokensService,
                changeTokenEnableInteract,
                myAddressRouter);
    }

    @Provides
    FetchTokensInteract provideFetchTokensInteract(TokenRepositoryType tokenRepository) {
        return new FetchTokensInteract(tokenRepository);
    }

    @Provides
    Erc20DetailRouter provideErc20DetailRouterRouter() {
        return new Erc20DetailRouter();
    }

    @Provides
    AssetDisplayRouter provideAssetDisplayRouter() {
        return new AssetDisplayRouter();
    }

    @Provides
    GenericWalletInteract provideGenericWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    ChangeTokenEnableInteract provideChangeTokenEnableInteract(TokenRepositoryType tokenRepository) {
        return new ChangeTokenEnableInteract(tokenRepository);
    }

    @Provides
    MyAddressRouter provideMyAddressRouter() {
        return new MyAddressRouter();
    }
}
