package com.testmvp.di.module;

import com.testmvp.WeatherAdapter;
import com.testmvp.di.scopes.ActivityScope;
import com.testmvp.weather.pojos.WeatherResList;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class WeatherAdapterModule {
    private List<WeatherResList> mList;
    public WeatherAdapterModule(List<WeatherResList> list){
        mList = list;
    }

    @ActivityScope
    @Provides
    WeatherAdapter provideAdapter(){
        return new WeatherAdapter(mList);
    }
}
