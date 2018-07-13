package com.testmvp.weather.apis;

import com.testmvp.weather.pojos.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterfaceWeatherService {
    @GET("forecast")
    Observable<WeatherResponse> doGetWeatherInfoFiveDays(@Query("q") String cityName,
                                                         @Query("APPID") String appId,
                                                         @Query("units") String unit);

    @GET("forecast")
    Observable<WeatherResponse> doGetWeatherInfo(@Query("q") String cityName,
                                                    @Query("cnt") String count,
                                                    @Query("APPID") String appId);
}
