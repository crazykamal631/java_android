package com.testmvp.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context mContext;

    public ContextModule(Context mContext) {
        this.mContext = mContext;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return mContext;
    }
}
