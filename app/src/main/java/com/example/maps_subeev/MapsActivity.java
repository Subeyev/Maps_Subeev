package com.example.maps_subeev;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setZoomGesturesEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                MarkerOptions marker = new MarkerOptions()
                        .position(new LatLng(point.latitude, point.longitude))
                        .title("Координаты:"+point.latitude+" "+point.longitude);
                mMap.addMarker(marker);
                System.out.println(point.latitude + "---" + point.longitude);
            }
        });

        // Add a marker in Vytegra and move the camera
        LatLng vytegra = new LatLng(61.006355, 36.449511);
        mMap.addMarker(new MarkerOptions().position(vytegra).title("Вытегра")).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vytegra, 9.0f));

    }

    public void ChangeType(View view){
        context = MapsActivity.this;

        if(mMap.getMapType()==GoogleMap.MAP_TYPE_NORMAL){
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            Toast.makeText(context, R.string.satellite, Toast.LENGTH_SHORT).show();
        }
        else if(mMap.getMapType()==GoogleMap.MAP_TYPE_SATELLITE){
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            Toast.makeText(context, R.string.hybrid, Toast.LENGTH_SHORT).show();
        }
        else if(mMap.getMapType()==GoogleMap.MAP_TYPE_HYBRID){
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            Toast.makeText(context, R.string.terrain, Toast.LENGTH_SHORT).show();
        }
        else if(mMap.getMapType()==GoogleMap.MAP_TYPE_TERRAIN){
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            Toast.makeText(context, R.string.normal, Toast.LENGTH_SHORT).show();
        }

    }


}