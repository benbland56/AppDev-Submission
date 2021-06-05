package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ordersGetMenuInfo extends AsyncTask<String, Integer, String> {

    public String selected;
    public String allMenus;
    public RecyclerView recv;
    public ArrayList<String> itemType;
    public ArrayList<String> itemName;
    public ArrayList<String> itemPrice;
    public Context context;
    public TextView progress;

    @Override
    protected String doInBackground(String... strings) {
        selected = strings[0];
        //server url
        String input = "http://cs2s.yorkdc.net/~jordan.towse/test.php";
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
                if(baos.size()%50 == 0){
                    publishProgress(baos.size());
                }
            }
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        allMenus = baos.toString();

        return allMenus;
    }

    protected void onProgressUpdate(Integer... prog){
        progress.setText("Downloading data: " + prog[0] + " bytes");
    }

    @Override
    protected void onPostExecute(String arraysAsList) {
        //arrays for data
        itemType = new ArrayList<>();
        itemName = new ArrayList<>();
        itemPrice = new ArrayList<>();

        //the array of menus after selected item and before the line break with no whitespace
        String menus = arraysAsList.substring(arraysAsList.indexOf(selected) + selected.length() + 3, arraysAsList.indexOf("<br />",(arraysAsList.indexOf(selected) + selected.length() + 3))-1);

        //catagory to class products as, relative to type array
        int catagory = 0;

        //split array into products and types and add to relevant arrays
        String[] product = menus.split("\\p{Punct}\\p{Punct} , \\p{Punct}\\p{Punct}");
        product[0] = product[0].substring(2);
        for (int x = 0; x < product.length; x++){
            if (product[x].startsWith(Character.toString('"'))){
                itemType.add(product[x]);
                catagory++;
            } else {
                String splitProducts[] = product[x].split("\\p{Punct} , \\p{Punct}");
                for(int y = 0; y < splitProducts.length; y++){
                        if (y == splitProducts.length-1){
                            splitProducts[y] = splitProducts[y].replaceAll("]]", "");
                        }
                        splitProducts[y] = Integer.toString(catagory) + " | " + splitProducts[y].replaceAll(Character.toString('"'),"");
                        String[] splitPrice = splitProducts[y].split(", ");
                        splitPrice[1] = "£" + splitPrice[1];
                        itemName.add(splitPrice[0]);
                        itemPrice.add(splitPrice[1]);
                }
            }
        }
        //send to recylcler
        recv.setLayoutManager(new LinearLayoutManager(context));
        orderRecyclerViewAdapter adapter = new orderRecyclerViewAdapter(context, itemPrice, itemName, itemType);
        recv.setAdapter(adapter);
        progress.setVisibility(View.GONE);
    }
}

