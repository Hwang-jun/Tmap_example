package com.example.tmap_example;

import androidx.appcompat.app.AppCompatActivity;

import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;
import com.skt.Tmap.poi_item.TMapPOIItem;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String API_KEY = "l7xx6c11a7128c354fa4a9944f2cab668afd"; // API_KEY

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TMapView tMapView = new TMapView(this); // T map View

        // API_KEY
        tMapView.setSKTMapApiKey(API_KEY);

        // Initial setting
        tMapView.setZoomLevel(17);
        tMapView.setIconVisibility(true);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);

        // T Map 보는데 사용되는 레이아웃
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutTmap);
        linearLayout.addView(tMapView);

        // 클릭 이벤트 설정
        tMapView.setOnClickListenerCallBack(new TMapView.OnClickListenerCallback() {
            @Override
            public boolean onPressEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                return false;
            }

            @Override
            public boolean onPressUpEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                return false;
            }
        });

        // 마커 생성
        TMapMarkerItem markerItem1 = new TMapMarkerItem();
        TMapPoint tMapPoint1 = new TMapPoint(35.1208, 129.1012); // 동명대 경도 위도

        // 마커 아이콘
        Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.map_pin_red);

        markerItem1.setIcon(bitmap);
        markerItem1.setPosition(0.5f, 1.0f);
        markerItem1.setTMapPoint(tMapPoint1);
        markerItem1.setName("동명대학교");
        tMapView.addMarkerItem("markerItem1", markerItem1);
        tMapView.setCenterPoint(129.1012, 35.1208);
    }
}