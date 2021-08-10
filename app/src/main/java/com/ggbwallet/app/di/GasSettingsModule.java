package com.ggbwallet.app.di;

import com.ggbwallet.app.service.TokensService;
import com.ggbwallet.app.viewmodel.GasSettingsViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class GasSettingsModule {

    @Provides
    public GasSettingsViewModelFactory provideGasSettingsViewModelFactory(TokensService svs) {
        return new GasSettingsViewModelFactory(svs);
    }
}
