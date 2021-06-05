package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class restaurantsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView buttonTitle;
    public ImageView image;

    restaurantsViewHolder(View itemView){
        super(itemView);
        buttonTitle = itemView.findViewById(R.id.restaurantName);
        image = itemView.findViewById(R.id.restaurantImage);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(!buttonTitle.getText().equals("Sorry there no restaurants found. Please try again.")) {
            Intent i = new Intent(view.getContext(), ordersSelect.class);
            i.putExtra("res", buttonTitle.getText());
            view.getContext().startActivity(i);
        }
    }
}
