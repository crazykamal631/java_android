package com.testmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Contract.ViewCallBacks {

    @BindView(R.id.text_view)
    TextView mTextView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    Contract.PresenterCallBacks mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new PresenterImpl(this, new Model());
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
