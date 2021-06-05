package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class restaurantsViewAdapter extends RecyclerView.Adapter<restaurantsViewHolder> {
    private LayoutInflater layoutInflater;
    private List<String> titleData;
    private List<Bitmap> imageData;

    restaurantsViewAdapter(Context context, List<String> titles, List<Bitmap> image){
        this.layoutInflater = LayoutInflater.from(context);
        this.titleData = titles;
        this.imageData = image;
    }

    public int getItemCount() {
        return titleData.size();
    }

    @Override
    public restaurantsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = layoutInflater.inflate(R.layout.recycler_restaurants, parent, false);
        restaurantsViewHolder vHolder = new restaurantsViewHolder(view);

        return vHolder;
    }

    public void onBindViewHolder(restaurantsViewHolder holder, int position){
        //int content = imageData.get(position);
        String title = titleData.get(position);

        holder.buttonTitle.setText(title);
        if(position < imageData.size()){
            Bitmap image = imageData.get(position);
            holder.image.setImageBitmap(image);
        }else {holder.image.setImageResource(R.drawable.index);}
    }

    public void clear() {
        int size = titleData.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                titleData.remove(0);
                imageData.remove(0);
            }

            notifyItemRangeRemoved(0, size);
        }
    }

}
