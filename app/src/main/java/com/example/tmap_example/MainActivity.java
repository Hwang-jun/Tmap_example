package com.example.tmap_example;

import androidx.appcompat.app.AppCompatActivity;

import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;
import com.skt.Tmap.poi_item.TMapPOIItem;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

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
        // View, GPS
        tMapView = new TMapView(this);
        tMapGPS = new TMapGpsManager(this);

        // Initial setting
        tMapView.setZoomLevel(17);
        tMapView.setIconVisibility(true);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN); // 한국어 사용

        tMapGPS.setMinTime(1000);
        tMapGPS.setMinDistance(10);
        tMapGPS.setProvider(tMapGPS.NETWORK_PROVIDER);
        tMapGPS.OpenGps();

        // T Map 보는데 사용되는 레이아웃
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutTmap);
        linearLayout.addView(tMapView);


        // gps 허가 요청
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

//        // 클릭 이벤트 설정
//        tMapView.setOnClickListenerCallBack(new TMapView.OnClickListenerCallback() {
//            @Override
//            public boolean onPressEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
//                return false;
//            }
//
//            @Override
//            public boolean onPressUpEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
//                return false;
//            }
//        });

        // 마커 생성
        TMapMarkerItem markerItem1 = new TMapMarkerItem();
        //TMapPoint tMapPoint1 = new TMapPoint(35.1208, 129.1012); // 동명대 경도 위도
        // TMapPoint tpoint = tMapView.getLocationPoint();
        // double Latitude = tpoint.getLatitude();
        // double Longitude = tpoint.getLongitude();

        // 마커 아이콘
        Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.map_pin_red);

        markerItem1.setIcon(bitmap);
    }

    @Override
    public void onLocationChange(Location location) {
        tMapView.setLocationPoint(location.getLongitude(), location.getLatitude());
        tMapView.setCenterPoint(location.getLongitude(), location.getLatitude());
    }
}