package com.ggbwallet.app.router;


import android.app.Activity;
import android.content.Intent;

import com.ggbwallet.app.C;
import com.ggbwallet.app.ui.Erc20DetailActivity;
import com.ggbwallet.app.entity.tokens.Token;
import com.ggbwallet.app.entity.Wallet;

public class Erc20DetailRouter {
    public void open(Activity context, String address, String symbol, int decimals, boolean isToken, Wallet wallet, Token token, boolean hasDefinition) {
        Intent intent = new Intent(context, Erc20DetailActivity.class);
        intent.putExtra(C.EXTRA_SENDING_TOKENS, isToken);
        intent.putExtra(C.EXTRA_CONTRACT_ADDRESS, address);
        intent.putExtra(C.EXTRA_SYMBOL, symbol);
        intent.putExtra(C.EXTRA_DECIMALS, decimals);
        intent.putExtra(C.Key.WALLET, wallet);
        intent.putExtra(C.EXTRA_TOKEN_ID, token);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        intent.putExtra(C.EXTRA_HAS_DEFINITION, hasDefinition);
        context.startActivityForResult(intent, C.TOKEN_SEND_ACTIVITY);
    }
}
