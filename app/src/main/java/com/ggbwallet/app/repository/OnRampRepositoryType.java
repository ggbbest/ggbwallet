package com.ggbwallet.app.repository;

import com.ggbwallet.app.entity.OnRampContract;
import com.ggbwallet.app.entity.tokens.Token;

public interface OnRampRepositoryType {
    String getUri(String address, Token token);

    OnRampContract getContract(Token token);
}
