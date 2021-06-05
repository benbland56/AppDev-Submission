package com.example.myapplication;

import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class deliveryMap extends FragmentActivity implements OnMapReadyCallback {

    public GoogleMap mMap;
    Handler handler = new Handler();
    int delay = 1000*5; //milliseconds

    //async task
    public recivieLocUpdatesAsync lu;

    public static boolean flag = false;
    public static boolean popup = false;

    public musicPlayerSwitchDialouge dialoug = new musicPlayerSwitchDialouge();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Map");
        setContentView(R.layout.activity_delivery_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.location_map);
        mapFragment.getMapAsync(this);
        Button b = findViewById(R.id.mediaButton);
        dialoug.b = b;
        dialoug.context = getBaseContext();
        b.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoom = 10;
        CameraUpdate point = CameraUpdateFactory.newLatLngZoom(new LatLng(54, -1), zoom);
        mMap.moveCamera(point);
        //refresh every 5 seconds
        handler.postDelayed(new Runnable(){
            public void run(){
                if (popup != true){
                lu = new recivieLocUpdatesAsync();
                lu.mMap = mMap;
                lu.execute();
                handler.postDelayed(this, delay);
                if(flag == true && popup == false){
                    dialoug.show(getSupportFragmentManager(), "popup");
                    popup = true;

                }
            }}
        }, delay);
    }
}
