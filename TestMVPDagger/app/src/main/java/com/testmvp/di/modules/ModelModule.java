package com.testmvp.di.modules;

import com.testmvp.Contract;
import com.testmvp.Model;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {

    @Singleton
    @Provides
    Contract.ModelCallBacks provideModelModule() {
        return new Model();
    }
}
