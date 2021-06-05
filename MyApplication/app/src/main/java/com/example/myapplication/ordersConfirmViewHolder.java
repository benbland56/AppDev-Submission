package com.example.myapplication;

import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ordersConfirmViewHolder extends RecyclerView.ViewHolder {
    public TextView item;
    public TextView itemPrice;

    ordersConfirmViewHolder(View itemView){
        super(itemView);
        item = itemView.findViewById(R.id.item);
        itemPrice = itemView.findViewById(R.id.itemPrice);

    }
}
