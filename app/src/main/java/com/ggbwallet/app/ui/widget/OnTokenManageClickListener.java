package com.ggbwallet.app.ui.widget;

import com.ggbwallet.app.entity.tokens.Token;

public interface OnTokenManageClickListener
{
    void onTokenClick(Token token, int position, boolean isChecked);
}
