package com.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ordersConfirmViewAdaptor extends RecyclerView.Adapter<ordersConfirmViewHolder> {

    private LayoutInflater layoutInflater;
    private List<String> item;
    private List<Double> itemPrice;

    ordersConfirmViewAdaptor(Context context, List<String> item, List<Double> itemPrice){
        this.layoutInflater = LayoutInflater.from(context);
        this.item = item;
        this.itemPrice = itemPrice;
    }

    public int getItemCount(){
        return item.size();
    }

    public ordersConfirmViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = layoutInflater.inflate(R.layout.ordersconfirmrecyclerrow, parent, false);
        ordersConfirmViewHolder vh = new ordersConfirmViewHolder(view);
        return vh;
    }

    public void onBindViewHolder(ordersConfirmViewHolder holder, int pos){
        String itemN = item.get(pos);
        String itemP = Double.toString(itemPrice.get(pos));
        holder.item.setText(itemN);

        //if price doesnt have a 1/100th number, add a 0 on the end
        if (itemP.substring(itemP.indexOf(".")).length() == 2){
            itemP = itemP + "0";
        }
        holder.itemPrice.setText("£" + itemP);

    }

}
