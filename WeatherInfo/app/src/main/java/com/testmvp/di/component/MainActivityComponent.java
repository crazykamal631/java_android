package com.testmvp.di.component;

import com.testmvp.MainActivity;
import com.testmvp.MainPresenter;
import com.testmvp.di.module.LocationManagerModule;
import com.testmvp.di.module.PlacesAdapterModule;
import com.testmvp.di.module.PresenterModule;
import com.testmvp.di.module.WeatherAdapterModule;
import com.testmvp.di.scopes.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = {PresenterModule.class,
        WeatherAdapterModule.class,
        LocationManagerModule.class,
        PlacesAdapterModule.class},
        dependencies = {AppComponent.class})
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);

    MainPresenter providePresenter();

}
