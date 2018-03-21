package sec.com.testdaggertwo;

import android.app.Application;

import sec.com.testdaggertwo.di.component.DaggerSharedPreferenceComponent;
import sec.com.testdaggertwo.di.component.SharedPreferenceComponent;
import sec.com.testdaggertwo.di.module.ContextModule;
import sec.com.testdaggertwo.di.module.SharedPrefereceModule;

public class MyApplication extends Application {
    private SharedPreferenceComponent sharedPreferenceComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferenceComponent = DaggerSharedPreferenceComponent.builder()
                .sharedPrefereceModule(new SharedPrefereceModule())
                .contextModule(new ContextModule(getApplicationContext()))
                .build();
    }

    public SharedPreferenceComponent getSharedPreferenceComponent() {
        return sharedPreferenceComponent;
    }
}
