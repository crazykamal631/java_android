package com.testmvp;

import android.app.Application;
import android.content.Context;

import com.testmvp.di.components.AppComponents;
import com.testmvp.di.components.DaggerAppComponents;
import com.testmvp.di.modules.ContextModule;
import com.testmvp.di.modules.ModelModule;

public class InitApplication extends Application {
    private AppComponents appComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponents = DaggerAppComponents
                .builder()
                .modelModule(new ModelModule())
                .contextModule(new ContextModule(this))
                .build();
    }

    public AppComponents getAppComponents() {
        return appComponents;
    }

    public static InitApplication getApplication(Context context) {
        return (InitApplication) context.getApplicationContext();
    }
}
