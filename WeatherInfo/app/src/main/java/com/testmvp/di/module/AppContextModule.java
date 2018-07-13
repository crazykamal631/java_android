package com.testmvp.di.module;

import android.content.Context;

import com.testmvp.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppContextModule {
    private Context mContext;

    public AppContextModule(Context context){
        mContext = context;
    }

    @ApplicationScope
    @Provides
    Context provideContext() {
        return mContext;
    }
}
