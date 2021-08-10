package com.ggbwallet.app.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import com.ggbwallet.app.interact.FetchWalletsInteract;
import com.ggbwallet.app.interact.GenericWalletInteract;
import com.ggbwallet.app.repository.CurrencyRepository;
import com.ggbwallet.app.repository.CurrencyRepositoryType;
import com.ggbwallet.app.repository.EthereumNetworkRepositoryType;
import com.ggbwallet.app.repository.LocaleRepository;
import com.ggbwallet.app.repository.LocaleRepositoryType;
import com.ggbwallet.app.repository.PreferenceRepositoryType;
import com.ggbwallet.app.repository.WalletRepositoryType;
import com.ggbwallet.app.router.AddTokenRouter;
import com.ggbwallet.app.router.ImportTokenRouter;
import com.ggbwallet.app.router.MyAddressRouter;
import com.ggbwallet.app.service.AnalyticsServiceType;
import com.ggbwallet.app.service.AssetDefinitionService;
import com.ggbwallet.app.service.TickerService;
import com.ggbwallet.app.service.TransactionsService;
import com.ggbwallet.app.viewmodel.HomeViewModelFactory;

@Module
class HomeModule {
    @Provides
    HomeViewModelFactory provideHomeViewModelFactory(
            PreferenceRepositoryType preferenceRepository,
            LocaleRepositoryType localeRepository,
            ImportTokenRouter importTokenRouter,
            AddTokenRouter addTokenRouter,
            AssetDefinitionService assetDefinitionService,
            GenericWalletInteract genericWalletInteract,
            FetchWalletsInteract fetchWalletsInteract,
            CurrencyRepositoryType currencyRepository,
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            Context context,
            MyAddressRouter myAddressRouter,
            TransactionsService transactionsService,
            TickerService tickerService,
            AnalyticsServiceType analyticsService) {
        return new HomeViewModelFactory(
                preferenceRepository,
                localeRepository,
                importTokenRouter,
                addTokenRouter,
                assetDefinitionService,
                genericWalletInteract,
                fetchWalletsInteract,
                currencyRepository,
                ethereumNetworkRepository,
                context,
                myAddressRouter,
                transactionsService,
                tickerService,
                analyticsService);
    }

    @Provides
    LocaleRepositoryType provideLocaleRepository(PreferenceRepositoryType preferenceRepository) {
        return new LocaleRepository(preferenceRepository);
    }

    @Provides
    AddTokenRouter provideAddTokenRouter() {
        return new AddTokenRouter();
    }

    @Provides
    ImportTokenRouter providesImportTokenRouter() { return new ImportTokenRouter(); }

    @Provides
    GenericWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new GenericWalletInteract(walletRepository);
    }

    @Provides
    FetchWalletsInteract provideFetchWalletInteract(WalletRepositoryType walletRepository) {
        return new FetchWalletsInteract(walletRepository);
    }

    @Provides
    CurrencyRepositoryType provideCurrencyRepository(PreferenceRepositoryType preferenceRepository) {
        return new CurrencyRepository(preferenceRepository);
    }

    @Provides
    MyAddressRouter provideMyAddressRouter() {
        return new MyAddressRouter();
    }
}
