package com.ggbwallet.app.di;

import com.ggbwallet.app.service.AssetDefinitionService;
import com.ggbwallet.app.viewmodel.TokenScriptManagementViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class TokenScriptManagementModule {
    @Provides
    TokenScriptManagementViewModelFactory tokenScriptManagementViewModelFactory(AssetDefinitionService assetDefinitionService)
    {
        return new TokenScriptManagementViewModelFactory(assetDefinitionService);
    }
}
