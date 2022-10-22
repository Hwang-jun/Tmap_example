package com.example.tmap_example;

import android.Manifest;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapView;

public class MainActivity extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback {

    String API_KEY = "l7xx6c11a7128c354fa4a9944f2cab668afd"; // API_KEY
    TMapView tMapView = null; // T map View
    TMapGpsManager tMapGPS = null; // GPS 사용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // API_KEY
        tMapView.setSKTMapApiKey(API_KEY);

        tMapView = new TMapView(this); // T map View
        tMapGPS = new TMapGpsManager(this); // GPS 사용

        // Initial setting
        tMapView.setZoomLevel(17);
        tMapView.setIconVisibility(true);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN); // 한국어 사용

        // T Map 보는데 사용되는 레이아웃
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayoutTmap);
        linearLayout.addView(tMapView);

        // gps 허가 요청 (* GPS 열기전에 허가 먼저 받아야함 순서가 중요)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        tMapGPS.setMinTime(1000);
        tMapGPS.setMinDistance(10);
        tMapGPS.setProvider(tMapGPS.NETWORK_PROVIDER);


        tMapGPS.OpenGps();

    }

    @Override
    public void onLocationChange(Location location) {
        tMapView.setLocationPoint(location.getLongitude(), location.getLatitude());
        tMapView.setCenterPoint(location.getLongitude(), location.getLatitude());
    }
}