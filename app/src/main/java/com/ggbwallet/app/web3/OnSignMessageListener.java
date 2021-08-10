package com.ggbwallet.app.web3;

import com.ggbwallet.token.entity.EthereumMessage;

public interface OnSignMessageListener {
    void onSignMessage(EthereumMessage message);
}
