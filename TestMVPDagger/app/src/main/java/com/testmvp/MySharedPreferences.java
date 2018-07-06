package com.testmvp;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class MySharedPreferences {
    private SharedPreferences mSharedPreferences;

    @Inject
    public MySharedPreferences(SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
    }

    public void putData(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public int getData(String key) {
        return mSharedPreferences.getInt(key, -1);
    }
}
