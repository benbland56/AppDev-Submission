package com.example.delivertunesdeliveryapp;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

public class sendToServerAsync extends AsyncTask<Void, Void, Boolean> {
    //async task to send data to server

    public String lat;
    public String lon;
    public boolean asyncPrev = false;

    @Override
    protected Boolean doInBackground(Void... voids) {
        asyncPrev = true;
        try {
            InetAddress address = InetAddress.getByName(new URL("http://kimb.us.to/").getHost());
            Socket toServer = new Socket(address, 14456);
            PrintWriter out = new PrintWriter(toServer.getOutputStream(), true);
            out.println(lat + " " + lon);
            toServer.close();
        } catch (UnknownHostException e) {
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        asyncPrev = false;
    }
}

