package com.testmvp;

import android.app.Application;
import android.content.Context;

import com.testmvp.di.component.AppComponent;
import com.testmvp.di.component.DaggerAppComponent;
import com.testmvp.di.module.AppContextModule;
import com.testmvp.di.module.GetQuoteModule;
import com.testmvp.di.module.RetrofitModule;

public class InitApplication extends Application {

    private AppComponent mAppComponent;

    public static InitApplication getApplication(Context context) {
        return (InitApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent
                .builder()
                .retrofitModule(new RetrofitModule())
                .getQuoteModule(new GetQuoteModule())
                .appContextModule(new AppContextModule(getApplicationContext()))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
