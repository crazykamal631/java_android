package com.testmvp.models;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetCurrentLocServiceImpl implements GetCurrentLoc, LocationListener {
    private static final String TAG = "GetCurrentLocServiceImplTestMVP789";
    private LocationManager mLocationManager = null;
    private Context mContext;
    private OnFinishedListener mOnFinishedListener;

    public GetCurrentLocServiceImpl(LocationManager locationManager,
                                    Context context) {
        this.mLocationManager = locationManager;
        this.mContext = context;
    }

    @Override
    public void getCurrentLocation(OnFinishedListener onFinishedListener) {
        mOnFinishedListener = onFinishedListener;
        if (ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mOnFinishedListener.onErrorNoPermission();
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager
                .GPS_PROVIDER, 5000, 10, this);
    }

    @Override
    public Boolean getGpsEnabled() {
        ContentResolver contentResolver = mContext
                .getContentResolver();
        return Settings.Secure
                .isLocationProviderEnabled(contentResolver,
                        LocationManager.GPS_PROVIDER);
    }

    @Override
    public void onLocationChanged(Location loc) {

        Toast.makeText(mContext, "Location changed : Lat: " +
                        loc.getLatitude() + " Lng: " + loc.getLongitude(),
                Toast.LENGTH_SHORT).show();
        String longitude = "Longitude: " + loc.getLongitude();
        Log.v(TAG, longitude);
        String latitude = "Latitude: " + loc.getLatitude();
        Log.v(TAG, latitude);

        /*----------to get City-Name from coordinates ------------- */
        String cityName = null;
        Geocoder gcd = new Geocoder(mContext,
                Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(loc.getLatitude(), loc
                    .getLongitude(), 1);
            if (addresses.size() > 0)
                System.out.println(addresses.get(0).getLocality());
            cityName = addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String s = longitude + "\n" + latitude +
                "\n\nMy Currrent City is: " + cityName;
        Log.d(TAG, "output -->" + s);
        mOnFinishedListener.onFinished(cityName);
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider,
                                int status, Bundle extras) {
    }
}
