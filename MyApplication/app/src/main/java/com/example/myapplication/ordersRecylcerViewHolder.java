package com.example.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ordersRecylcerViewHolder extends RecyclerView.ViewHolder {
    public TextView itemPrice;
    public CheckBox itemName;
    public TextView itemType;

    ordersRecylcerViewHolder(View itemView){
        super((itemView));
        itemPrice = itemView.findViewById(R.id.itemPrice);
        itemName = itemView.findViewById(R.id.itemName);
        itemType = itemView.findViewById(R.id.itemType);

        //adds relevent info to store when iten is checked, and removes it when unchecked
        itemName.setOnClickListener(e ->{
            if (itemName.isChecked()){
                store.order.add((String) itemName.getText());
                store.orderPrice.add(Double.parseDouble((itemPrice.getText().toString()).substring(1)));
            } else{
                store.order.remove((String) itemName.getText());
                store.orderPrice.remove((Double.parseDouble((itemPrice.getText().toString()).substring(1))));
            }
        });
    }
}
