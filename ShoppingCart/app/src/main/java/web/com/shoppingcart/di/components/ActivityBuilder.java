package web.com.shoppingcart.di.components;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import web.com.shoppingcart.di.scopes.ActivityScope;
import web.com.shoppingcart.ui.home.MainActivity;
import web.com.shoppingcart.ui.home.MainActivityModule;

@Module
public abstract class ActivityBuilder {
    //@ContributesAndroidInjector(modules = {MainActivityModule.class, HomeFragmentBuilder.class, CategoryFragmentBuilder.class})

    @ActivityScope
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity bindMainActivity();

    /*@ContributesAndroidInjector(modules = {DetailActivityModule.class, DetailFragmentProvider.class})
    abstract DetailActivity bindDetailActivity();*/
}
