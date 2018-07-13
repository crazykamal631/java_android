package com.testmvp.weather.pojos;

public class WeatherResponse {
    private WeatherResCity city;
    private int cnt;
    private String cod;
    private double message;
    private WeatherResList[] list;

    public WeatherResCity getCity() {
        return this.city;
    }

    public void setCity(WeatherResCity city) {
        this.city = city;
    }

    public int getCnt() {
        return this.cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getCod() {
        return this.cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return this.message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public WeatherResList[] getList() {
        return this.list;
    }

    public void setList(WeatherResList[] list) {
        this.list = list;
    }
}
