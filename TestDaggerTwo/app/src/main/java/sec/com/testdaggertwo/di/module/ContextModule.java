package sec.com.testdaggertwo.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import sec.com.testdaggertwo.di.MyApplicationScope;


@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @MyApplicationScope
    @Provides
    Context provideContext() {
        return context;
    }
}
