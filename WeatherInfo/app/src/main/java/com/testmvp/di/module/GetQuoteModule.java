package com.testmvp.di.module;

import com.testmvp.di.scopes.ApplicationScope;
import com.testmvp.models.WeatherInfo;
import com.testmvp.models.WeatherInfoImpl;
import com.testmvp.weather.apis.APIInterfaceWeatherService;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class GetQuoteModule {

    @Inject
    @Provides
    @ApplicationScope
    WeatherInfo provideGetQuote(APIInterfaceWeatherService apiInterfaceWeatherService) {
        return new WeatherInfoImpl(apiInterfaceWeatherService);
    }
}
