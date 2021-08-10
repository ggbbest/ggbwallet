package com.ggbwallet.app.ui.widget;

import java.io.Serializable;

import com.ggbwallet.app.entity.DApp;

public interface OnDappClickListener extends Serializable {
    void onDappClick(DApp dapp);
}
