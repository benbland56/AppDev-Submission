package com.example.myapplication;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class recivieLocUpdatesAsync extends AsyncTask<Void,Void,String> {
    //async task to get location from server
    public GoogleMap mMap;

    @Override
    protected String doInBackground(Void... voids) {
        //server url containing lat and lon, or arrived
        String input = "http://kimb.us.to/latlong.txt";
        //get data from server
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            URL latlon = new URL(input);
            URLConnection conn = latlon.openConnection();
            InputStream is = conn.getInputStream();
            BufferedInputStream stream = new BufferedInputStream(is);
            int streamOut = stream.read();
            while (streamOut != -1){
                baos.write(streamOut);
                streamOut = stream.read();
            }
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return baos.toString();
    }

    @Override
    protected void onPostExecute(String ecoords) {
        //if there isnt any data on the server ignore, otherwise decode latlong and place marker on map
        mMap.clear();
        if (ecoords.equals("Arrived Arrived")){
            deliveryMap.flag = true;
        } else if (ecoords != null) {
            double lat = Double.parseDouble(ecoords.substring(0, ecoords.indexOf(" ")));
            double lon = Double.parseDouble(ecoords.substring(ecoords.indexOf(" ")));
            LatLng deliv = new LatLng(lat, lon);
            mMap.addMarker(new MarkerOptions().position(deliv).title("Delivery"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(deliv));
        }
    }
}
