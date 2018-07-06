package com.testmvp.di.components;

import com.testmvp.Contract;
import com.testmvp.di.modules.ContextModule;
import com.testmvp.di.modules.ModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, ModelModule.class})
public interface AppComponents {
    Contract.ModelCallBacks getModel();
}
