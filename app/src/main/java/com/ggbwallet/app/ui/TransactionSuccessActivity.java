package com.ggbwallet.app.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ggbwallet.app.C;
import com.ggbwallet.app.R;
import com.ggbwallet.app.entity.StandardFunctionInterface;
import com.ggbwallet.app.widget.CopyTextView;
import com.ggbwallet.app.widget.FunctionButtonBar;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by JB on 4/12/2020.
 */
public class TransactionSuccessActivity extends BaseActivity implements StandardFunctionInterface
{
    private String transactionHash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_success);

        transactionHash = getIntent().getStringExtra(C.EXTRA_TXHASH);
        CopyTextView hashText = findViewById(R.id.tx_hash);
        hashText.setText(transactionHash);

        toolbar();

        setTitle(getString(R.string.empty));

        FunctionButtonBar functionBar = findViewById(R.id.layoutButtons);
        functionBar.setupFunctions(this, new ArrayList<>(Collections.singletonList(R.string.action_show_tx_details)));
        functionBar.revealButtons();
    }

    @Override
    public void handleClick(String action, int actionId)
    {
        Intent intent = new Intent();
        intent.putExtra(C.EXTRA_TXHASH, transactionHash);
        setResult(RESULT_OK, intent);
        finish();
    }
}
