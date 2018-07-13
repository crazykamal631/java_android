package com.testmvp.di.module;

import android.content.Context;
import android.location.LocationManager;

import com.testmvp.di.scopes.ActivityScope;
import com.testmvp.models.GetCurrentLoc;
import com.testmvp.models.GetCurrentLocServiceImpl;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class LocationManagerModule {

    @Inject
    @Provides
    @ActivityScope
    LocationManager provideLocationManager(Context context){
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @ActivityScope
    @Provides
    GetCurrentLoc provideCurrentLocation(LocationManager locationManager,
                                         Context context) {
        return new GetCurrentLocServiceImpl(locationManager,
                context);
    }
}
