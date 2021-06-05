package com.example.myapplication;

import android.graphics.Bitmap;
import java.util.ArrayList;

public class store {
    public static ArrayList<Address> addresses = new ArrayList<>();
    public static String restaurants[] = new String[40];
    public static ArrayList<Bitmap> restaurantImages = new ArrayList<>();
    public static String query = "";
    public static ArrayList<String> order = new ArrayList<>();
    public static ArrayList<Double> orderPrice = new ArrayList<>();
    public static String allRestaurants[] = new String[40];
    public static ArrayList<Bitmap> allRestaurantImages = new ArrayList<>();
    public static Boolean resturantError = false;

    public static void clearResturants(){

        for(int i =0; i < restaurants.length; i++){
            restaurants[i] = null;
        }
    }
}
