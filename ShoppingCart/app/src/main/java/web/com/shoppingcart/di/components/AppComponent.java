package web.com.shoppingcart.di.components;

import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import web.com.shoppingcart.InitApplication;
import web.com.shoppingcart.di.module.AppContextModule;
import web.com.shoppingcart.di.module.RetrofitModule;
import web.com.shoppingcart.di.module.RoomModule;
import web.com.shoppingcart.di.scopes.ApplicationScope;
import web.com.shoppingcart.server.api.APIInterfaceWeatherService;

@ApplicationScope
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilder.class,
        RetrofitModule.class,
        AppContextModule.class,
        RoomModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(InitApplication initApplication);

    APIInterfaceWeatherService provideServerApi();

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }

    /*Context provideAppContext();
    void inject(InitApplication initApplication);
    CategoryDao providesCategoryDao();*/
}
