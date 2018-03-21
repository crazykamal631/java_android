package sec.com.testdaggertwo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    AppSharedPref appSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MyApplication) getApplicationContext()).getSharedPreferenceComponent()
                .inject(this);
        appSharedPref.putData("info", 10);
    }
}
