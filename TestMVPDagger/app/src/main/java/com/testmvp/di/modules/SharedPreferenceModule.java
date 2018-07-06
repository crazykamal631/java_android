package com.testmvp.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferenceModule {

    @Inject
    @Singleton
    @Provides
    SharedPreferences provideSharedPreference(Context context) {
        return context.getSharedPreferences("test_pref", Context.MODE_PRIVATE);
    }
}
