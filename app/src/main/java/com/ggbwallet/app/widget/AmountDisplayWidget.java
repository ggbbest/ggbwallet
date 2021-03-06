package com.ggbwallet.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ggbwallet.app.R;
import com.ggbwallet.app.entity.tokens.Token;
import com.ggbwallet.app.repository.TokensRealmSource;
import com.ggbwallet.app.repository.entity.RealmTokenTicker;
import com.ggbwallet.app.service.TickerService;
import com.ggbwallet.app.service.TokensService;
import com.ggbwallet.app.util.BalanceUtils;
import com.ggbwallet.app.util.LocaleUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Locale;

import javax.annotation.Nullable;

import io.realm.Realm;

/**
 * Created by Jenny Jingjing Li on 21/03/2021
 * */

public class AmountDisplayWidget extends LinearLayout {

    private final Locale deviceSettingsLocale = LocaleUtils.getDeviceLocale(getContext());
    private TextView amount;

    public AmountDisplayWidget(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);

        inflate(context, R.layout.item_amount_display, this);
        amount = findViewById(R.id.text_amount);
    }

    public void setAmountFromString(String displayStr)
    {
        amount.setText(displayStr);
    }

    public void setAmountFromBigInteger(BigInteger txAmount, String token)
    {
        NumberFormat decimalFormat = NumberFormat.getInstance(deviceSettingsLocale);
        setAmountFromString(decimalFormat.format(txAmount) + ' ' + token);
    }

    private String getValueString(BigInteger amount, Token token, TokensService tokensService)
    {
        String formattedValue = BalanceUtils.getScaledValueMinimal(amount, token.tokenInfo.decimals);
        //fetch ticker if required
        try (Realm realm = tokensService.getTickerRealmInstance())
        {
            RealmTokenTicker rtt = realm.where(RealmTokenTicker.class)
                    .equalTo("contract", TokensRealmSource.databaseKey(token.tokenInfo.chainId, token.isEthereum() ? "eth" : token.getAddress().toLowerCase()))
                    .findFirst();

            if (rtt != null)
            {
                //calculate equivalent fiat
                BigDecimal cryptoRate = new BigDecimal(rtt.getPrice());
                BigDecimal cryptoAmount = BalanceUtils.subunitToBase(amount, token.tokenInfo.decimals);
                return getContext().getString(R.string.fiat_format, formattedValue, token.getSymbol(),
                        TickerService.getCurrencyString(cryptoAmount.multiply(cryptoRate).doubleValue()),
                        rtt.getCurrencySymbol());
            }
        }
        catch (Exception e)
        {
            //
        }

        return getContext().getString(R.string.total_cost, formattedValue, token.getSymbol());
    }

    public void setAmountUsingToken(BigInteger amountValue, Token token, TokensService tokensService)
    {
        amount.setText(getValueString(amountValue, token, tokensService));
    }
}