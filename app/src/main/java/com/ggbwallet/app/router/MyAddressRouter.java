package com.ggbwallet.app.router;

import android.content.Context;
import android.content.Intent;

import com.ggbwallet.app.C;
import com.ggbwallet.app.ui.MyAddressActivity;
import com.ggbwallet.app.entity.tokens.Token;
import com.ggbwallet.app.entity.Wallet;

public class MyAddressRouter {

    public void open(Context context, Wallet wallet) {
        Intent intent = new Intent(context, MyAddressActivity.class);
        intent.putExtra(C.Key.WALLET, wallet);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        context.startActivity(intent);
    }

    public void open(Context context, Wallet wallet, Token token) {
        Intent intent = new Intent(context, MyAddressActivity.class);
        intent.putExtra(C.Key.WALLET, wallet);
        intent.putExtra(C.EXTRA_TOKEN_ID, token);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        context.startActivity(intent);
    }
}
