package com.testmvp.di.module;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.testmvp.di.scopes.ActivityScope;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class PlacesAdapterModule {
    private List<String> items;

    public PlacesAdapterModule(List<String> items) {
        this.items = items;
    }

    @ActivityScope
    @Provides
    @Inject
    ArrayAdapter provideSpinnerAdapter(Context context) {
        return new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, items);
        //return new SpinnerAdapter(context, android.R.layout.simple_spinner_item, items);
    }
}
