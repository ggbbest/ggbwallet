package com.ggbwallet.app.di;

import com.ggbwallet.app.interact.FetchTransactionsInteract;
import com.ggbwallet.app.repository.OnRampRepositoryType;
import com.ggbwallet.app.repository.TokenRepositoryType;
import com.ggbwallet.app.repository.TransactionRepositoryType;
import com.ggbwallet.app.router.MyAddressRouter;
import com.ggbwallet.app.service.AssetDefinitionService;
import com.ggbwallet.app.service.TokensService;
import com.ggbwallet.app.viewmodel.Erc20DetailViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class Erc20DetailModule {
    @Provides
    Erc20DetailViewModelFactory provideErc20DetailViewModelFactory(MyAddressRouter myAddressRouter,
                                                                   FetchTransactionsInteract fetchTransactionsInteract,
                                                                   AssetDefinitionService assetDefinitionService,
                                                                   TokensService tokensService,
                                                                   OnRampRepositoryType onRampRepository) {
        return new Erc20DetailViewModelFactory(myAddressRouter,
                fetchTransactionsInteract,
                assetDefinitionService,
                tokensService,
                onRampRepository);
    }

    @Provides
    MyAddressRouter provideMyAddressRouter() {
        return new MyAddressRouter();
    }

    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepositoryType,
                                                               TokenRepositoryType tokenRepositoryType) {
        return new FetchTransactionsInteract(transactionRepositoryType, tokenRepositoryType);
    }
}
