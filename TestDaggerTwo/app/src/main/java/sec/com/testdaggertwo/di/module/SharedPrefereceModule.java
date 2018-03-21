package sec.com.testdaggertwo.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import sec.com.testdaggertwo.di.MyApplicationScope;

@Module
public class SharedPrefereceModule {

    @MyApplicationScope
    @Provides
    @Inject
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences("PrefName", Context.MODE_PRIVATE);
    }
}
