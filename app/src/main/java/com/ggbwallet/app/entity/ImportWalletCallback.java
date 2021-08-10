package com.ggbwallet.app.entity;
import com.ggbwallet.app.entity.cryptokeys.KeyEncodingType;
import com.ggbwallet.app.service.KeyService;

public interface ImportWalletCallback
{
    void walletValidated(String address, KeyEncodingType type, KeyService.AuthenticationLevel level);
}
