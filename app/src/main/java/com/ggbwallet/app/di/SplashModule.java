package com.ggbwallet.app.di;

import dagger.Module;
import dagger.Provides;
import com.ggbwallet.app.interact.FetchWalletsInteract;

import com.ggbwallet.app.repository.CurrencyRepository;
import com.ggbwallet.app.repository.CurrencyRepositoryType;
import com.ggbwallet.app.repository.LocaleRepository;
import com.ggbwallet.app.repository.LocaleRepositoryType;
import com.ggbwallet.app.repository.PreferenceRepositoryType;
import com.ggbwallet.app.repository.WalletRepositoryType;
import com.ggbwallet.app.service.AssetDefinitionService;
import com.ggbwallet.app.service.KeyService;
import com.ggbwallet.app.viewmodel.SplashViewModelFactory;

@Module
public class SplashModule {

    @Provides
    SplashViewModelFactory provideSplashViewModelFactory(FetchWalletsInteract fetchWalletsInteract,
                                                         PreferenceRepositoryType preferenceRepository,
                                                         LocaleRepositoryType localeRepository,
                                                         KeyService keyService,
                                                         AssetDefinitionService assetDefinitionService,
                                                         CurrencyRepositoryType currencyRepository) {
        return new SplashViewModelFactory(
                fetchWalletsInteract,
                preferenceRepository,
                localeRepository,
                keyService,
                assetDefinitionService,
                currencyRepository);
    }

    @Provides
    FetchWalletsInteract provideFetchWalletInteract(WalletRepositoryType walletRepository) {
        return new FetchWalletsInteract(walletRepository);
    }

    @Provides
    LocaleRepositoryType provideLocaleRepositoryType(PreferenceRepositoryType preferenceRepository) {
        return new LocaleRepository(preferenceRepository);
    }

    @Provides
    CurrencyRepositoryType provideCurrencyRepositoryType(PreferenceRepositoryType preferenceRepository) {
        return new CurrencyRepository(preferenceRepository);
    }
}
