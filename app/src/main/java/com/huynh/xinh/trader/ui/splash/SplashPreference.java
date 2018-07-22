package com.huynh.xinh.trader.ui.splash;

import android.content.SharedPreferences;

import javax.inject.Inject;

import static com.huynh.xinh.data.PreferenceConstant.PREF_SYNC_EXCHANGE_COIN;
import static com.huynh.xinh.data.PreferenceConstant.PREF_SYNC_MARKET;

public class SplashPreference {
    private final SharedPreferences preferences;

    @Inject
    SplashPreference(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public boolean isExchangeCoinSynced() {
        return preferences.getBoolean(PREF_SYNC_EXCHANGE_COIN, false);
    }

    public void setExchangeCoinSynced(boolean synced) {
        preferences.edit().putBoolean(PREF_SYNC_EXCHANGE_COIN, synced).apply();
    }

    public boolean isMarketSynced() {
        return preferences.getBoolean(PREF_SYNC_MARKET, false);
    }

    public void setMarketSynced(boolean synced) {
        preferences.edit().putBoolean(PREF_SYNC_MARKET, synced).apply();
    }
}
