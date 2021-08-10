package com.ggbwallet.app.viewmodel;

import com.ggbwallet.app.entity.NetworkInfo;
import com.ggbwallet.app.repository.EthereumNetworkRepositoryType;
import com.ggbwallet.app.service.TokensService;

public class MyAddressViewModel extends BaseViewModel {
    private final EthereumNetworkRepositoryType ethereumNetworkRepository;
    private final TokensService tokenService;

    MyAddressViewModel(
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            TokensService tokensService) {
        this.ethereumNetworkRepository = ethereumNetworkRepository;
        this.tokenService = tokensService;
    }

    public TokensService getTokenService() {
        return tokenService;
    }

    public EthereumNetworkRepositoryType getEthereumNetworkRepository() {
        return ethereumNetworkRepository;
    }

    public NetworkInfo getNetworkByChain(int chainId) {
        return ethereumNetworkRepository.getNetworkByChain(chainId);
    }
}
