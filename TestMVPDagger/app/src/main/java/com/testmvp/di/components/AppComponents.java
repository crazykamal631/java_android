package com.testmvp.di.components;

import android.content.SharedPreferences;

import com.testmvp.Contract;
import com.testmvp.di.modules.ContextModule;
import com.testmvp.di.modules.ModelModule;
import com.testmvp.di.modules.SharedPreferenceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class,
        ModelModule.class,
        SharedPreferenceModule.class})
public interface AppComponents {
    Contract.ModelCallBacks getModel();

    SharedPreferences getSharedPref();
}
