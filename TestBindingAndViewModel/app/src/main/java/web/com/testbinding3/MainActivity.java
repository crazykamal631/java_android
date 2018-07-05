package web.com.testbinding3;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import web.com.testbinding3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = ViewModelProviders.of(this).get(User.class);

        ActivityMainBinding activityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setLifecycleOwner(this);
        activityMainBinding.setUser(user);
        activityMainBinding.setClickHandler(new DoMagic());

    }

    public class DoMagic {
        public void doClick(View view) {
            Toast.makeText(getApplicationContext(), "hello",
                    Toast.LENGTH_SHORT).show();
            user.getName().postValue("pavan");
            user.getEmail().postValue("pavan@gmail.com");
        }
    }
}
