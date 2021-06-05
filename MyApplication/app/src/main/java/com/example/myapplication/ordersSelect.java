package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ordersSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_select);

        Intent prevIntent = getIntent();
        String title = prevIntent.getStringExtra("res");

        setTitle(title);
        ordersGetMenuInfo ogmi = new ordersGetMenuInfo();
        ogmi.recv = findViewById(R.id.review);
        ogmi.context = this;
        ogmi.progress = findViewById(R.id.progress);
        ogmi.execute(title);

        Button confirm = findViewById(R.id.confirmOrder);
        Button cancel = findViewById(R.id.cancelOrder);

        confirm.setOnClickListener(e ->{
            //wont let progress unless buying something
            if(store.orderPrice.size() > 0) {
                Intent i = new Intent(getBaseContext(), orderConf.class);
                startActivityForResult(i, 0);
            }
        });

        cancel.setOnClickListener(e -> {
            store.orderPrice.clear();
            store.order.clear();
            Intent i = new Intent(this, restaurants.class);
            startActivity(i);
        });
    }
}
