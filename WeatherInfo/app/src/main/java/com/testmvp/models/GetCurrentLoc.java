package com.testmvp.models;

public interface GetCurrentLoc {
    interface OnFinishedListener{
        void onFinished(String response);
        void onErrorNoPermission();
    }
    public void getCurrentLocation(OnFinishedListener onFinishedListener);

    public Boolean getGpsEnabled();
}
