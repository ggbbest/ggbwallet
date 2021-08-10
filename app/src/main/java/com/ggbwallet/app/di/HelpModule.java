package com.ggbwallet.app.di;

import com.ggbwallet.app.viewmodel.HelpViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class HelpModule {
    @Provides
    HelpViewModelFactory provideMarketplaceViewModelFactory() {
        return new HelpViewModelFactory();
    }
}
