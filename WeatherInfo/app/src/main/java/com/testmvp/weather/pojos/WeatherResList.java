package com.testmvp.weather.pojos;

public class WeatherResList {
    private int dt;
    private String dt_txt;
    private WeatherResListWeather[] weather;
    private WeatherResListMain main;
    private WeatherResListClouds clouds;
    private WeatherResListSys sys;
    private WeatherResListWind wind;

    public int getDt() {
        return this.dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public String getDt_txt() {
        return this.dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public WeatherResListWeather[] getWeather() {
        return this.weather;
    }

    public void setWeather(WeatherResListWeather[] weather) {
        this.weather = weather;
    }

    public WeatherResListMain getMain() {
        return this.main;
    }

    public void setMain(WeatherResListMain main) {
        this.main = main;
    }

    public WeatherResListClouds getClouds() {
        return this.clouds;
    }

    public void setClouds(WeatherResListClouds clouds) {
        this.clouds = clouds;
    }

    public WeatherResListSys getSys() {
        return this.sys;
    }

    public void setSys(WeatherResListSys sys) {
        this.sys = sys;
    }

    public WeatherResListWind getWind() {
        return this.wind;
    }

    public void setWind(WeatherResListWind wind) {
        this.wind = wind;
    }
}
