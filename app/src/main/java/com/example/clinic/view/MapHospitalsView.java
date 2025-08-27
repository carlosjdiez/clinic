package com.example.clinic.view;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clinic.R;
import com.example.clinic.util.MapUtil;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;

import java.util.ArrayList;
import java.util.List;

public class MapHospitalsView extends AppCompatActivity implements Style.OnStyleLoaded {

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;

    private static class Hospital {
        final String name; final double lat; final double lng;
        Hospital(String n, double la, double ln) { name = n; lat = la; lng = ln; }
    }

    private final List<Hospital> hospitalList = new ArrayList<Hospital>() {{
        add(new Hospital("H. Univ. Miguel Servet", 41.6329, -0.9009));
        add(new Hospital("H. Clínico Lozano Blesa", 41.6374, -0.9262));
        add(new Hospital("H. Royo Villanova",      41.6737, -0.8654));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_hospitals);

        mapView = findViewById(R.id.mapViewHospitals);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);


        pointAnnotationManager = MapUtil.initializePointAnnotationManager(mapView);
    }

    private void viewHospitals() {
        for (Hospital h : hospitalList) {
            addMarker(h.name, h.lat, h.lng);
        }
    }

    private void addMarker(String message, double latitude, double longitude) {
        PointAnnotationOptions marker = new PointAnnotationOptions()
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker))
                .withTextField(message)
                .withPoint(Point.fromLngLat(longitude, latitude));
        pointAnnotationManager.create(marker);
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {
        viewHospitals();
    }
}
