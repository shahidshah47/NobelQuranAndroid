package com.quranic.islam.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager extends DialogFactory {
    private SharedPreferences getPrefs(Activity mAct) {
        return mAct.getSharedPreferences(APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    private SharedPreferences getPrefs(Context mAct) {
        return mAct.getSharedPreferences(APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    public int getMyIntPref(Activity mAct, String prefName, int defVal) {
        return getPrefs(mAct).getInt(prefName, defVal);
    }

    public void setMyIntPref(Activity mAct, String prefName, int value) {
        getPrefs(mAct).edit().putInt(prefName, value).apply();
    }

    public String getMyStringPref(Activity mAct, String prefName, String defVal) {
        return getPrefs(mAct).getString(prefName, defVal);
    }

    public String getMyStringPref(Context mAct, String prefName, String defVal) {
        return getPrefs(mAct).getString(prefName, defVal);
    }

    public void setMyStringPref(Activity mAct, String prefName, String value) {
        getPrefs(mAct).edit().putString(prefName, value).apply();
    }

    public void setMyStringPref(Context mAct, String prefName, String value) {
        getPrefs(mAct).edit().putString(prefName, value).apply();
    }

    public boolean getMyBooleanPref(Activity mAct, String prefName, boolean defVal) {
        return getPrefs(mAct).getBoolean(prefName, defVal);
    }

    public void setMyBooleanPref(Activity mAct, String prefName, boolean value) {
        getPrefs(mAct).edit().putBoolean(prefName, value).apply();
    }

    public long getMyLongPref(Activity mAct, String prefName, long defVal) {
        return getPrefs(mAct).getLong(prefName, defVal);
    }

    public void setMyLongPref(Activity mAct, String prefName, long value) {
        getPrefs(mAct).edit().putLong(prefName, value).apply();
    }

    public void removePref(Activity mAct, String prefName) {
        getPrefs(mAct).edit().remove(prefName).apply();
    }
}