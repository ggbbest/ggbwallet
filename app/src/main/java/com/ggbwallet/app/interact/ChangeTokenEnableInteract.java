package com.ggbwallet.app.interact;

import com.ggbwallet.app.repository.TokenRepositoryType;
import com.ggbwallet.app.entity.tokens.Token;
import com.ggbwallet.app.entity.Wallet;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChangeTokenEnableInteract {
    private final TokenRepositoryType tokenRepository;

    public ChangeTokenEnableInteract(TokenRepositoryType tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Completable setEnable(Wallet wallet, Token token, boolean enabled) {
        return tokenRepository.setEnable(wallet, token, enabled)
                .andThen(tokenRepository.setVisibilityChanged(wallet, token))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
