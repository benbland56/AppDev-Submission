package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class orderConf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_conf);
        setTitle("View Your Order");

        RecyclerView rv = findViewById(R.id.recylcer);
        rv.setLayoutManager(new LinearLayoutManager(this));
        ordersConfirmViewAdaptor adapter = new ordersConfirmViewAdaptor(this, store.order, store.orderPrice);
        rv.setAdapter(adapter);

        Button yesConf = findViewById(R.id.yesConf);
        Button noConf = findViewById(R.id.noConf);
        TextView totalPrice = findViewById(R.id.totalPrice);

        Double total = 0.00;

        //add all of the prices together for the selcted order
        for (int i = 0; i < store.orderPrice.size(); i++){
            total = total + store.orderPrice.get(i);
        }
        total+= 7.99;

        //round to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String totalS = df.format(total);

        //if the total doesnt have a 1/100th digit, add a 0
        if (totalS.substring(totalS.indexOf(".")).length() == 2){
            totalS = totalS + "0";
        }

        totalPrice.setText("£" + totalS);

        yesConf.setOnClickListener(e ->{
            Intent i = new Intent(getBaseContext(), deliveryMap.class);
            startActivityForResult(i, 0);
        });

        noConf.setOnClickListener(e ->{
            Intent i = new Intent(getBaseContext(), ordersSelect.class);
            store.orderPrice.clear();
            store.order.clear();
            startActivityForResult(i, 0);
        });


    }
}
