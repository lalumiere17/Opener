package com.example.demofon.models;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {
    private Context context;

    private static final String ACCOUNT_NFO = "ACCOUNT_INFO";
    private static final String TOKEN_ID = "TOKEN_ID";

    public static final String TOKEN_ID_DEFAULT = null;

    private SharedPreferences accountInfo;

    public Settings(Context context) {
        this.context = context;
        accountInfo = context.getSharedPreferences(ACCOUNT_NFO, Context.MODE_PRIVATE);
    }

    public void setTokenId(String tokenId) {
        accountInfo
                .edit()
                .putString(TOKEN_ID, tokenId)
                .apply();
    }

    public String getTokenId() {
        return  accountInfo.getString(TOKEN_ID, TOKEN_ID_DEFAULT);
    }
}
