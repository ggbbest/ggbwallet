package com.ggbwallet.app.router;

import android.content.Context;
import android.content.Intent;

import com.ggbwallet.app.C;
import com.ggbwallet.app.ui.RedeemSignatureDisplayActivity;
import com.ggbwallet.app.ui.widget.entity.TicketRangeParcel;
import com.ggbwallet.app.entity.tokens.Token;
import com.ggbwallet.app.entity.Wallet;

/**
 * Created by James on 25/01/2018.
 */

public class RedeemSignatureDisplayRouter {
    public void open(Context context, Wallet wallet, Token token, TicketRangeParcel range) {
        Intent intent = new Intent(context, RedeemSignatureDisplayActivity.class);
        intent.putExtra(C.Key.WALLET, wallet);
        intent.putExtra(C.Key.TICKET, token);
        intent.putExtra(C.Key.TICKET_RANGE, range);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
}