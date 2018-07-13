package com.testmvp;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.testmvp.weather.pojos.WeatherResList;

import java.util.List;

public interface MainView extends MvpView {
    public void showProgress();

    public void hideProgress();

    public void setText(String string);

    public void updateList(List<WeatherResList> tempList, String cityName);

    public void showMessage(String response);

    public void refreshAdapter(String cityName);

    public void clearAdapters();

}
