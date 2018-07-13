package com.testmvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.testmvp.di.component.DaggerMainActivityComponent;
import com.testmvp.di.component.MainActivityComponent;
import com.testmvp.di.module.PlacesAdapterModule;
import com.testmvp.di.module.PresenterModule;
import com.testmvp.di.module.WeatherAdapterModule;
import com.testmvp.weather.apis.APIInterfaceWeatherService;
import com.testmvp.weather.pojos.WeatherResList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpActivity<MainView, MainPresenter>
        implements MainView {
    private static final String TAG = "MainActivityTestMPVP789";

    @BindView(R.id.text_view)
    TextView mTextView;

    @BindView(R.id.edit_text)
    EditText mEditText;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    WeatherAdapter mWeatherAdapter;

    @Inject
    APIInterfaceWeatherService apiInterfaceWeatherService;

    @Inject
    Context mContext;

    @Inject
    ArrayAdapter mSpinnerAdapter;

    private List<String> mPlacesList = new ArrayList<>();

    private MainActivityComponent mainActivityComponent;

    private Map<String, List<WeatherResList>> mapOfWeather = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<WeatherResList> weatherResList = new ArrayList<>();
        mainActivityComponent =
                DaggerMainActivityComponent
                        .builder()
                        .appComponent(InitApplication.getApplication(this).getAppComponent())
                        .presenterModule(new PresenterModule())
                        .weatherAdapterModule(new WeatherAdapterModule(weatherResList))
                        .placesAdapterModule(new PlacesAdapterModule(mPlacesList))
                        .build();
        mainActivityComponent.injectMainActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        spinner.setAdapter(mSpinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                Log.d(TAG,"onItemSelected");
                // On selecting a spinner item
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                presenter.loadDataUsingCityNames(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mWeatherAdapter);

    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return mainActivityComponent.providePresenter();
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

    @Override
    public void updateList(List<WeatherResList> tempList, String cityName) {
        mapOfWeather.put(cityName, tempList);
        updateSpinner(cityName);
        Log.d(TAG, "weatherResList-@@##->" + mapOfWeather.size());
    }

    @Override
    public void refreshAdapter(String cityName){
        if(mapOfWeather.containsKey(cityName)){
            List<WeatherResList> tempList = mapOfWeather.get(cityName);
            mWeatherAdapter.clearData();
            mWeatherAdapter.updateList(tempList, cityName);
            mWeatherAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void clearAdapters() {
        mPlacesList.clear();
        mSpinnerAdapter.notifyDataSetChanged();

        mWeatherAdapter.clearData();
        mWeatherAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String response) {
        Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button_click)
    void onButtonClick() {
        String text = mEditText.getText().toString().trim();
        //presenter.onButtonClick("Delhi, Mumbai");
        presenter.onButtonClick(text);
    }

    @OnClick(R.id.button_click_current_loc)
    void onButtonClickCurrentLocWeatherInfo() {
        presenter.checkGpsStatus();
    }

    private void updateSpinner(String string){
        mPlacesList.add(string);
        mSpinnerAdapter.notifyDataSetChanged();
    }
}
