package sec.com.testdaggertwo.di.component;

import dagger.Component;
import sec.com.testdaggertwo.MainActivity;
import sec.com.testdaggertwo.di.MyApplicationScope;
import sec.com.testdaggertwo.di.module.ContextModule;
import sec.com.testdaggertwo.di.module.SharedPrefereceModule;

@MyApplicationScope
@Component(modules = {SharedPrefereceModule.class, ContextModule.class})
public interface SharedPreferenceComponent {
    void inject(MainActivity mainActivity);
}
