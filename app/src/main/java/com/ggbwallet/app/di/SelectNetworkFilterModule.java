package com.ggbwallet.app.di;

import com.ggbwallet.app.repository.EthereumNetworkRepositoryType;
import com.ggbwallet.app.repository.PreferenceRepositoryType;
import com.ggbwallet.app.service.TokensService;
import com.ggbwallet.app.viewmodel.SelectNetworkFilterViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class SelectNetworkFilterModule {
    @Provides
    SelectNetworkFilterViewModelFactory provideSelectNetworkFilterViewModelFactory(EthereumNetworkRepositoryType networkRepositoryType,
                                                                                   TokensService tokensService,
                                                                                   PreferenceRepositoryType preferenceRepositoryType) {
        return new SelectNetworkFilterViewModelFactory(networkRepositoryType, tokensService, preferenceRepositoryType);
    }
}
