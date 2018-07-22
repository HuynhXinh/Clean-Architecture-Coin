package com.huynh.xinh.trader.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.Primitives;
import com.huynh.xinh.trader.TraderApplication;

public class PreferencesUtil {
    private static final String TAG = "PreferencesUtil";

    private static final String PREF_NAME = "pref_trader";

    private static PreferencesUtil instance;

    private SharedPreferences prefs;

    private PreferencesUtil() {
        prefs = TraderApplication.getInstance().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    synchronized public static PreferencesUtil getInstance() {
        if (instance == null) {
            instance = new PreferencesUtil();
        }
        return instance;
    }

    public void setBooleanValue(String key, boolean value) {
        prefs.edit().putBoolean(key, value).apply();
    }

    public boolean getBooleanValue(String key, boolean defaultValue) {
        return prefs.getBoolean(key, defaultValue);
    }

    public void setStringValue(String key, String value) {
        prefs.edit().putString(key, value).apply();
    }

    public String getStringValue(String key, String defaultValue) {
        return prefs.getString(key, defaultValue);
    }

    public void set(String key, Object value) {
        prefs.edit().putString(key, new Gson().toJson(value)).apply();
    }

    public <T> T get(String key, Class<T> mModelClass) {
        Object object = null;
        try {
            object = new Gson().fromJson(prefs.getString(key, ""), mModelClass);
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }
        return Primitives.wrap(mModelClass).cast(object);
    }

    public void setIntValue(String key, int value) {
        prefs.edit().putInt(key, value).apply();
    }

    public int getIntValue(String key, int defaultValue) {
        return prefs.getInt(key, defaultValue);
    }

    public void setLongValue(String key, long value) {
        prefs.edit().putLong(key, value).apply();
    }

    public long getLongValue(String key, long defaultValue) {
        return prefs.getLong(key, defaultValue);
    }

    public void setFloatValue(String key, float value) {
        prefs.edit().putFloat(key, value).apply();
    }

    public float getFloatValue(String key, float defaultValue) {
        return prefs.getFloat(key, defaultValue);
    }

    public void removeValue(String key) {
        prefs.edit().remove(key).apply();
    }

    public double getDoubleValue(String key, double defaultValue) {
        return Double.parseDouble(getStringValue(key, String.valueOf(defaultValue)));
    }

    public void setDoubleValue(String key, double defaultValue) {
        setStringValue(key, String.valueOf(defaultValue));
    }

    public boolean contain(String key) {
        return prefs.contains(key);
    }

}
