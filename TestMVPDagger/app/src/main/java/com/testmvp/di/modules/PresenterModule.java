package com.testmvp.di.modules;

import com.testmvp.Contract;
import com.testmvp.PresenterImpl;
import com.testmvp.di.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    private Contract.ViewCallBacks mainView;

    public PresenterModule(Contract.ViewCallBacks mainView) {
        this.mainView = mainView;
    }

    @ActivityScope
    @Provides
    Contract.ViewCallBacks provideView(){
        return mainView;
    }

    @ActivityScope
    @Provides
    Contract.PresenterCallBacks providePresenter(Contract.ViewCallBacks mainView, Contract.ModelCallBacks model) {
        return new PresenterImpl(mainView, model);
    }
}
