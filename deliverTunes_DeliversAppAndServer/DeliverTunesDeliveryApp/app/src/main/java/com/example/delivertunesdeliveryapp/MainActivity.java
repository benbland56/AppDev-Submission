package com.example.delivertunesdeliveryapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //async task
    //ALLOW LOCATION ACCESS MANUALLY THROUGH ANDROID OR WONT WORK
    sendToServerAsync ss;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define controls
        Button startB = findViewById(R.id.startButton);
        Button stopB = findViewById(R.id.stopButton);
        TextView latText = findViewById(R.id.latText);
        TextView lonText = findViewById(R.id.lonText);
        Button arrived = findViewById(R.id.arrivedAtDest);

        ss = new sendToServerAsync();



        //some of the following code was copied from https://developer.android.com/training/location/receive-location-updates
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                if(!ss.asyncPrev) {
                    //send latlong to async task
                    ss = new sendToServerAsync();
                    latText.setText("lat: " + location.getLatitude());
                    lonText.setText("lon: " + location.getLongitude());
                    ss.lat = String.valueOf(location.getLatitude());
                    ss.lon = String.valueOf(location.getLongitude());
                    ss.execute();
                }
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            public void onProviderEnabled(String provider) {}
            public void onProviderDisabled(String provider) {}
        };

        startB.setOnClickListener(e -> {
            //on start click, recive updates from  gps
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000*5, 0, locationListener);
        });
        stopB.setOnClickListener(e -> {
            //on stop click, stop reciving gps updates
            locationManager.removeUpdates(locationListener);
            latText.setText("lat:");
            lonText.setText("lon:");
        });
        arrived.setOnClickListener(e ->{
            //on delivery click, tell server to tell customer food has arrived
            locationManager.removeUpdates(locationListener);
            ss = new sendToServerAsync();
            latText.setText("lat: Arrived");
            lonText.setText("lon: Arrived");
            ss.lat="Arrived";
            ss.lon="Arrived";
            ss.execute();
        });
    }
}
