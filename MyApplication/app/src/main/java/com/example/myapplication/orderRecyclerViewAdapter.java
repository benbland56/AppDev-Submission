package com.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class orderRecyclerViewAdapter extends RecyclerView.Adapter<ordersRecylcerViewHolder> {
    private LayoutInflater layinf;
    private List<String> itemPrice,itemName,itemType;
    private int lastInt = -1;

    orderRecyclerViewAdapter(Context context, List<String> price, List<String> name, List<String> type){
        this.layinf = LayoutInflater.from(context);
        this.itemPrice = price;
        this.itemName = name;
        this.itemType = type;
    }

    public int getItemCount(){
        return itemName.size();
    }

    public ordersRecylcerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = layinf.inflate(R.layout.ordersrecylcerrow, parent, false);
       ordersRecylcerViewHolder viewHolder = new ordersRecylcerViewHolder(view);
       return viewHolder;
    }

    public void onBindViewHolder(ordersRecylcerViewHolder holder, int position){
        //check the prefix and set the cat title to relevent one
        String price = itemPrice.get(position);
        String name = itemName.get(position);
        String type = null;
        if(lastInt == -1){
            lastInt++;
            type = itemType.get(lastInt).substring(1);
        } else if (lastInt == Integer.parseInt(itemName.get(position).substring(0,1))-1) {
            holder.itemType.setVisibility(View.GONE);
        } else if (lastInt != Integer.parseInt(itemName.get(position).substring(0,1))-1) {
            lastInt++;
            type = itemType.get(lastInt).substring(1);
        }

        name = name.substring(3);
        holder.itemType.setText(type);
        holder.itemPrice.setText(price);
        holder.itemName.setText(name);
    }

}
