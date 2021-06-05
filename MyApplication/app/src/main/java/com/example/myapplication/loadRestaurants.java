package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

public class loadRestaurants extends AsyncTask<Void, Void, String[]> {

    String result[] = new String[40];
    @Override
    protected String[] doInBackground(Void... params) {

        Log.d("Start", "");
        String str = "";
        try {
            URL url = new URL("http://cs2s.yorkdc.net/~james.clarke1/restaurants.php");
            HttpURLConnection mUrlConnection = (HttpURLConnection) url.openConnection();
            mUrlConnection.setDoInput(true);

            StringBuilder stringBuilder = new StringBuilder();

            String line = "";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mUrlConnection.getInputStream(), "UTF-8"));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            str = stringBuilder+"";
            result = str.split(",");

            store.restaurants = result.clone();
            store.allRestaurants = result.clone();

            URL imageQuery = new URL("http://cs2s.yorkdc.net/~james.clarke1/restaurantImages.php");
            HttpURLConnection mUrlConnectionImage = (HttpURLConnection) imageQuery.openConnection();
            mUrlConnectionImage.setDoInput(true);

            StringBuilder stringBuilderImage = new StringBuilder();

            String lineImage = "";
            BufferedReader bufferedReaderImage = new BufferedReader(new InputStreamReader(mUrlConnectionImage.getInputStream(), "UTF-8"));
            while ((lineImage = bufferedReaderImage.readLine()) != null) {
                stringBuilderImage.append(lineImage);
            }
            String images = stringBuilderImage+"";
            String imageResults[] = images.split(",");
            Log.d("Image", images);
            for(String i: imageResults) {
                Log.d("URL", i);
                try {
                    URL imageUrl = new URL(i);
                    URLConnection conn = imageUrl.openConnection();
                    InputStream is = conn.getInputStream();
                    BufferedInputStream stream = new BufferedInputStream(is);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int intInput = stream.read();
                    while (intInput != -1) {
                        baos.write(intInput);
                        intInput = stream.read();
                    }
                    byte[] imageBytes = baos.toByteArray();
                    Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    store.restaurantImages.add(bmp);
                    store.allRestaurantImages.add(bmp);
                    Log.d("Image", "Ran");
                } catch (Exception e){
                    store.restaurantImages.add(null);
                    store.allRestaurantImages.add(null);
                }
            }

        } catch (java.net.MalformedURLException e){
            Log.d("URLException", e.getMessage());
        } catch (java.io.IOException f) {
            Log.d("IOException", f.getMessage());
        } catch (android.os.NetworkOnMainThreadException g){
            Log.d("NetworkOnMainThreadException", g.getMessage()+"");
        }

        return result;

    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

    }
}
