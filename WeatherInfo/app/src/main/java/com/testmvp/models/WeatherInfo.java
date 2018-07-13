package com.testmvp.models;

import com.testmvp.weather.pojos.WeatherResponse;

public interface WeatherInfo {
    interface OnFinishedListener{
        void onFinished(WeatherResponse response);
        void onError();
    }
    void getWeatherInfo(OnFinishedListener onFinishedListener, String place);
}
