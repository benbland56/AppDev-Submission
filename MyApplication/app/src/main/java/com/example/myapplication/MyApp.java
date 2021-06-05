package com.example.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyApp extends Application {

    private static Context mContext;

    public void onCreate(){
        super.onCreate();
        mContext = this.getApplicationContext();
    }

    public static Context getAppContext(){
        return mContext;
    }
}
