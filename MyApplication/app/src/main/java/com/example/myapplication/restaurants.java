package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class restaurants extends AppCompatActivity {

    static TextView textView;
    //public static restaurantsViewAdapter adapter;
    //public static RecyclerView recyclerView;
    public static restaurants context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("Store in restaurants", Arrays.toString(store.restaurants));

        SearchView simpleSearchView = (SearchView) findViewById(R.id.search); // inititate a search view
        simpleSearchView.setQuery(store.query, false);
        ArrayList<String> titles = new ArrayList<String>();
        ArrayList<Bitmap> contents = new ArrayList<Bitmap>(store.restaurantImages);

            for (String r : store.restaurants) {
                if (r != null) {
                    titles.add(r);
                }
            }

            if(store.resturantError){
                titles.add("Sorry there no restaurants found. Please try again.");

            }

            RecyclerView recyclerView = findViewById(R.id.rView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            restaurantsViewAdapter adapter = new restaurantsViewAdapter(this, titles, contents);
            recyclerView.setAdapter(adapter);
            context = this;
            simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    store.query = query + "";
                    Log.d("Query", query);
                    AsyncTask filterRestaurants = new filterRestaurants().execute(query + "");

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (newText.equals("")) {
                        this.onQueryTextSubmit("");
                    }
                    return false;
                }
            });


    }

    public static void update(){
        Intent i = context.getIntent();
        context.finish();
        context.startActivity(i);
    }
}
