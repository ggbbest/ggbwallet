package com.ggbwallet.app.interact;

import com.ggbwallet.app.entity.ContractType;
import com.ggbwallet.app.entity.tokens.Token;
import com.ggbwallet.app.entity.tokens.TokenInfo;
import com.ggbwallet.app.entity.Wallet;
import com.ggbwallet.app.repository.TokenRepositoryType;

import io.reactivex.Observable;

public class AddTokenInteract {
    private final TokenRepositoryType tokenRepository;

    public AddTokenInteract(
            TokenRepositoryType tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Observable<Token> add(TokenInfo tokenInfo, ContractType type, Wallet wallet) {
        return tokenRepository
                        .addToken(wallet, tokenInfo, type).toObservable();
    }
}
