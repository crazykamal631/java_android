package com.testmvp;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.testmvp.models.GetCurrentLoc;
import com.testmvp.models.WeatherInfo;
import com.testmvp.weather.pojos.WeatherResList;
import com.testmvp.weather.pojos.WeatherResponse;

import java.util.Arrays;
import java.util.List;

public class MainPresenter extends MvpBasePresenter<MainView>
        implements WeatherInfo.OnFinishedListener,
        GetCurrentLoc.OnFinishedListener{
    private static final String TAG = "MainPresenterTestMVP789";
    private WeatherInfo getQuote;

    private GetCurrentLoc mGetCurrentLoc;

    public MainPresenter(WeatherInfo getQuote, GetCurrentLoc getCurrentLoc){
        this.getQuote = getQuote;
        this.mGetCurrentLoc = getCurrentLoc;
    }
    public void onButtonClick(String string) {
        Log.d(TAG,"onButtonClick");
        if(string != null && !string.trim().equals("")){
            ifViewAttached(MainView::showProgress);
            ifViewAttached(MainView::clearAdapters);
            getQuote.getWeatherInfo(this, string);
        }else{
            if(isViewAttached()){
                getView().showMessage("Wrong City Name");
            }
        }
    }

    @Override
    public void onFinished(WeatherResponse response) {
        Log.d(TAG,"onFinished update ui-->");
        ifViewAttached(MainView::hideProgress);
        if(isViewAttached()){
            List<WeatherResList> tempList = Arrays.asList(response.getList());
            String cityName = response.getCity().getName();
            getView().updateList(tempList, cityName);
        }
    }

    @Override
    public void onError() {
        if(isViewAttached()){
            ifViewAttached(MainView::hideProgress);
            getView().showMessage("Wrong City Name !");
        }
    }

    private void getWeatherInfoCurrentLoc() {
        ifViewAttached(MainView::showProgress);
        getCurrentLocation();
    }

    public void loadDataUsingCityNames(String cityName) {
        if(isViewAttached()){
            getView().refreshAdapter(cityName);
        }
    }

    public void checkGpsStatus(){
        ifViewAttached(MainView::clearAdapters);
        boolean isGpsEnabled = mGetCurrentLoc.getGpsEnabled();
        if(!isGpsEnabled){
            if(isViewAttached()){
                getView().showMessage("Please enable GPS");
            }
        }else{
            // find current city
            getWeatherInfoCurrentLoc();
        }
    }

    private void getCurrentLocation(){
        mGetCurrentLoc.getCurrentLocation(this);
    }

    @Override
    public void onFinished(String response) {
        Log.d(TAG, "--found current city-->"+response);
        if(response != null) {
            getQuote.getWeatherInfo(this, response);
        }else{
            if(isViewAttached()){
                getView().showMessage("Current location is null, try again");
            }
        }
    }

    @Override
    public void onErrorNoPermission() {
        if(isViewAttached()){
            ifViewAttached(MainView::hideProgress);
            getView().showMessage("Please give location permission");
        }
    }
}
