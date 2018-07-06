package com.testmvp.di.components;

import com.testmvp.MainActivity;
import com.testmvp.di.di.scopes.ActivityScope;
import com.testmvp.di.modules.PresenterModule;

import dagger.Component;

@ActivityScope
@Component(modules = {PresenterModule.class}, dependencies = {AppComponents.class})
public interface ActivityComponents {
    void inject(MainActivity mainActivity);
}
