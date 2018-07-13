package com.testmvp.di.module;

import com.testmvp.MainPresenter;
import com.testmvp.di.scopes.ActivityScope;
import com.testmvp.models.GetCurrentLoc;
import com.testmvp.models.WeatherInfo;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    @Provides
    @ActivityScope
    @Inject
    MainPresenter providePresenter(WeatherInfo getQuote, GetCurrentLoc getCurrentLoc) {
        return new MainPresenter(getQuote, getCurrentLoc);
    }
}
