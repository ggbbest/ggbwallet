package com.ggbwallet.app.di;

import com.ggbwallet.app.interact.ChangeTokenEnableInteract;
import com.ggbwallet.app.repository.TokenRepositoryType;
import com.ggbwallet.app.router.AddTokenRouter;
import com.ggbwallet.app.service.AssetDefinitionService;
import com.ggbwallet.app.service.TokensService;
import com.ggbwallet.app.viewmodel.TokenManagementViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class TokenManagementModule {
    @Provides
    TokenManagementViewModelFactory provideTokenManagementViewModelFactory(
            TokenRepositoryType tokenRepository,
            ChangeTokenEnableInteract changeTokenEnableInteract,
            AddTokenRouter addTokenRouter,
            AssetDefinitionService assetDefinitionService,
            TokensService tokensService)
    {
        return new TokenManagementViewModelFactory(tokenRepository, changeTokenEnableInteract, addTokenRouter, assetDefinitionService, tokensService);
    }

    @Provides
    ChangeTokenEnableInteract provideChangeTokenEnableInteract(TokenRepositoryType tokenRepository) {
        return new ChangeTokenEnableInteract(tokenRepository);
    }

    @Provides
    AddTokenRouter provideAddTokenRouter() {
        return new AddTokenRouter();
    }
}
