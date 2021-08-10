package com.ggbwallet.app.repository.entity;

import com.ggbwallet.app.entity.tokens.Token;

import java.math.BigInteger;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.ggbwallet.app.repository.TokensRealmSource.EVENT_CARDS;

/**
 * Created by JB on 17/12/2020.
 */
public class RealmTransfer extends RealmObject
{
    private String hash;
    private String tokenAddress;
    private String eventName;
    private String transferDetail;

    public String getHash()
    {
        return hash;
    }
    public void setHash(String hash) { this.hash = hash; }

    public String getTokenAddress()
    {
        return tokenAddress;
    }

    public void setTokenAddress(String address)
    {
        tokenAddress = address;
    }

    public void setTransferDetail(String transferDetail)
    {
        this.transferDetail = transferDetail;
    }

    public String getTransferDetail()
    {
        return transferDetail;
    }

    public String getEventName()
    {
        return eventName;
    }

    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }

    public static RealmResults<RealmAuxData> getEventListener(Realm realm, Token token, BigInteger tokenId, int historyCount, long timeLimit)
    {
        return getEventQuery(realm, token, tokenId, historyCount, timeLimit).findAllAsync();
    }

    public static RealmQuery<RealmAuxData> getEventQuery(Realm realm, Token token, BigInteger tokenId, int historyCount, long timeLimit)
    {
        String tokenIdHex = tokenId.toString(16);
        return realm.where(RealmAuxData.class)
                .endsWith("instanceKey", EVENT_CARDS)
                .sort("resultTime", Sort.DESCENDING)
                .greaterThan("resultTime", timeLimit)
                .equalTo("chainId", token.tokenInfo.chainId)
                .beginGroup().equalTo("tokenId", "0").or().equalTo("tokenId", tokenIdHex).endGroup()
                .equalTo("tokenAddress", token.getAddress())
                .limit(historyCount);
    }
}
