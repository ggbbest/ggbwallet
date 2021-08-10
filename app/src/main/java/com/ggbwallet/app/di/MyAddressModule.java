package com.ggbwallet.app.di;

import dagger.Module;
import dagger.Provides;
import com.ggbwallet.app.repository.EthereumNetworkRepositoryType;
import com.ggbwallet.app.service.TokensService;
import com.ggbwallet.app.viewmodel.MyAddressViewModelFactory;

@Module
class MyAddressModule {
    @Provides
    MyAddressViewModelFactory provideMyAddressViewModelFactory(
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            TokensService tokensService) {
        return new MyAddressViewModelFactory(
                ethereumNetworkRepository,
                tokensService);
    }
}
