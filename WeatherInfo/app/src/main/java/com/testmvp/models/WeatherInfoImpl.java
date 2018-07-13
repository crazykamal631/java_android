package com.testmvp.models;

import android.util.Log;

import com.testmvp.AppConstants;
import com.testmvp.weather.apis.APIInterfaceWeatherService;
import com.testmvp.weather.pojos.WeatherResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class WeatherInfoImpl implements WeatherInfo {
    private static final String TAG = "WeatherInfoImplTestMVP789";

    private APIInterfaceWeatherService apiInterfaceWeatherService;

    public WeatherInfoImpl(APIInterfaceWeatherService apiInterfaceWeatherService) {
        this.apiInterfaceWeatherService = apiInterfaceWeatherService;
    }

    @Override
    public void getWeatherInfo(OnFinishedListener onFinishedListener, String places) {
        if(places != null) {
            if (places.trim().contains(",")) {
                String array[] = places.split(",");
                for (String st : array) {
                    Log.d(TAG,"look weather info for-->"+st);
                    getInfo(onFinishedListener, st.trim());
                }
            } else {
                getInfo(onFinishedListener, places.trim());
            }
        }
    }

    private void getInfo(OnFinishedListener onFinishedListener, String place) {
        apiInterfaceWeatherService.
                doGetWeatherInfoFiveDays(place, AppConstants.appId, AppConstants.temp_unit_celsius)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "--onSubscribe--");
                    }

                    @Override
                    public void onNext(WeatherResponse weatherResponse) {
                        Log.d(TAG, "--onNext-WeatherResList size-" + weatherResponse.getList().length);
                        onFinishedListener.onFinished(weatherResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "--onError--");
                        onFinishedListener.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
