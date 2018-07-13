package com.testmvp.di.component;

import android.content.Context;

import com.testmvp.di.module.AppContextModule;
import com.testmvp.di.module.GetQuoteModule;
import com.testmvp.di.module.RetrofitModule;
import com.testmvp.di.scopes.ApplicationScope;
import com.testmvp.models.WeatherInfo;
import com.testmvp.weather.apis.APIInterfaceWeatherService;

import dagger.Component;

@ApplicationScope
@Component(modules = {RetrofitModule.class, GetQuoteModule.class, AppContextModule.class})
public interface AppComponent {
    APIInterfaceWeatherService getApiInterface();
    WeatherInfo provideGetQuote();
    Context provideAppContext();
}
