package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

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

public class filterRestaurants extends AsyncTask<String, Void, String[]> {
        String result[] = new String[40];
        Boolean error = false;
        @Override
        protected String[] doInBackground(String... r) {

            Log.d("Start", r[0]);
            String str = "";

            if(r[0].length() != 0) {
                try {
                    URL url;

                    url = new URL("http://cs2s.yorkdc.net/~james.clarke1/searchRestaurants.php?r=" + r[0]);

                    Log.d("Url", url + "");
                    HttpURLConnection mUrlConnection = (HttpURLConnection) url.openConnection();
                    mUrlConnection.setDoInput(true);

                    StringBuilder stringBuilder = new StringBuilder();

                    String line = "";
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mUrlConnection.getInputStream(), "UTF-8"));
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);

                        Log.d("Query Result", line);
                    }
                    str = stringBuilder + "";
                    result = str.split(",");
                    Log.d("Resturants", Arrays.toString(result));

                    //Clears stores
                    store.restaurantImages.clear();
                    Arrays.fill(store.restaurants, null);

                    int index = 0;
                    if(result.length < 0) {
                        for (String i : result) {
                            int pos = Integer.parseInt(i);
                            store.restaurantImages.add(store.allRestaurantImages.get(pos - 1));
                            store.restaurants[index] = store.allRestaurants[pos - 1];
                            index++;
                        }
                        store.resturantError = false;
                    }else{
                        store.resturantError = true;
                    }
                    Log.d("Array restaurants", Arrays.toString(store.restaurants));
                    Log.d("Array allRestaurants", Arrays.toString(store.allRestaurants));


                } catch (java.net.MalformedURLException e) {
                    Log.d("URLException", e.getMessage());
                } catch (java.io.IOException f) {
                    Log.d("IOException", f.getMessage());
                } catch (android.os.NetworkOnMainThreadException g) {
                    Log.d("NetworkOnMainThreadException", g.getMessage() + "");
                }
            }else{
                store.restaurants = store.allRestaurants.clone();
                store.restaurantImages = (ArrayList<Bitmap>) store.allRestaurantImages.clone();
                store.resturantError = false;
            }
            return result;

        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String[] result){
            Log.d("Ran ", "onPostExecute");
            restaurants.update();
        }
    }


