package com.testmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.testmvp.di.components.ActivityComponents;
import com.testmvp.di.components.DaggerActivityComponents;
import com.testmvp.di.modules.PresenterModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Contract.ViewCallBacks {

    private static final String TAG = "MainActivity789";
    @BindView(R.id.text_view)
    TextView mTextView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Inject
    Contract.PresenterCallBacks mPresenter;

    @Inject
    MySharedPreferences mMySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ActivityComponents activityComponents = DaggerActivityComponents
                .builder()
                .appComponents(InitApplication.getApplication(this).getAppComponents())
                .presenterModule(new PresenterModule(this))
                .build();
        activityComponents.inject(this);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setText(String string) {
        mTextView.setText(string);
    }

    @OnClick(R.id.click)
    void handleClick() {
        mPresenter.onButtonClick();
        checkPref();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    private void checkPref() {
        mMySharedPreferences.putData("test", 100);
        int i = mMySharedPreferences.getData("test");
        Log.d(TAG, "value in pref-->" + i);
    }
}
