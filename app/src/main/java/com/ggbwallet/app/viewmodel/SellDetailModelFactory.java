package com.ggbwallet.app.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.ggbwallet.app.interact.CreateTransactionInteract;
import com.ggbwallet.app.interact.FindDefaultNetworkInteract;
import com.ggbwallet.app.interact.GenericWalletInteract;
import com.ggbwallet.app.router.SellDetailRouter;
import com.ggbwallet.app.service.AssetDefinitionService;
import com.ggbwallet.app.service.KeyService;
import com.ggbwallet.app.service.MarketQueueService;

/**
 * Created by James on 21/02/2018.
 */

public class SellDetailModelFactory implements ViewModelProvider.Factory {

    private FindDefaultNetworkInteract findDefaultNetworkInteract;
    private GenericWalletInteract genericWalletInteract;
    private MarketQueueService marketQueueService;
    private CreateTransactionInteract createTransactionInteract;
    private SellDetailRouter sellDetailRouter;
    private KeyService keyService;
    private AssetDefinitionService assetDefinitionService;

    public SellDetailModelFactory(FindDefaultNetworkInteract findDefaultNetworkInteract,
                                  GenericWalletInteract genericWalletInteract,
                                        MarketQueueService marketQueueService,
                                  CreateTransactionInteract createTransactionInteract,
                                  SellDetailRouter sellDetailRouter,
                                  KeyService keyService,
                                  AssetDefinitionService assetDefinitionService) {
        this.findDefaultNetworkInteract = findDefaultNetworkInteract;
        this.genericWalletInteract = genericWalletInteract;
        this.marketQueueService = marketQueueService;
        this.createTransactionInteract = createTransactionInteract;
        this.sellDetailRouter = sellDetailRouter;
        this.keyService = keyService;
        this.assetDefinitionService = assetDefinitionService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SellDetailViewModel(findDefaultNetworkInteract, genericWalletInteract, marketQueueService, createTransactionInteract, sellDetailRouter, keyService, assetDefinitionService);
    }
}

