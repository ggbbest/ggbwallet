package com.ggbwallet.app.viewmodel;

import android.app.Activity;
import android.content.Intent;

import com.ggbwallet.app.entity.NetworkInfo;
import com.ggbwallet.app.repository.EthereumNetworkBase;
import com.ggbwallet.app.repository.EthereumNetworkRepositoryType;
import com.ggbwallet.app.repository.PreferenceRepositoryType;
import com.ggbwallet.app.service.TokensService;
import com.ggbwallet.app.ui.SelectNetworkFilterActivity;

import java.util.List;

public class SelectNetworkViewModel extends BaseViewModel {
    private final EthereumNetworkRepositoryType networkRepository;
    private final TokensService tokensService;
    private final PreferenceRepositoryType preferenceRepository;

    public SelectNetworkViewModel(EthereumNetworkRepositoryType ethereumNetworkRepositoryType,
                                  TokensService tokensService,
                                  PreferenceRepositoryType preferenceRepository)
    {
        this.networkRepository = ethereumNetworkRepositoryType;
        this.tokensService = tokensService;
        this.preferenceRepository = preferenceRepository;
    }

    public NetworkInfo[] getNetworkList()
    {
        return networkRepository.getAvailableNetworkList();
    }

    public List<Integer> getFilterNetworkList()
    {
        return networkRepository.getFilterNetworkList();
    }

    public void openSelectNetworkFilters(Activity ctx, int requestCode)
    {
        Intent intent = new Intent(ctx, SelectNetworkFilterActivity.class);
        ctx.startActivityForResult(intent, requestCode);
    }

    public boolean mainNetActive()
    {
        return preferenceRepository.isActiveMainnet();
    }

    public boolean hasShownTestNetWarning()
    {
        return preferenceRepository.hasShownTestNetWarning();
    }

    public void setShownTestNetWarning()
    {
        preferenceRepository.setShownTestNetWarning();
    }

    public NetworkInfo getNetworkByChain(int chainId)
    {
        return networkRepository.getNetworkByChain(chainId);
    }

    public boolean isMainNet(int networkId)
    {
        return EthereumNetworkBase.hasRealValue(networkId);
    }
}
