package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;

public class musicPlayerSwitchDialouge extends DialogFragment {
    public Button b;
    public Context context;

    @SuppressLint("NewApi")
    @Override
    public Dialog onCreateDialog(Bundle instance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Order has Arrived")
                .setPositiveButton("Go to Music Player", (dialog, id) -> openMediaPlayer())
                .setCancelable(false)
                .setOnDismissListener(e -> deliveryMap.popup = false)
                .setOnCancelListener(e -> deliveryMap.popup = false);
        b.setVisibility(View.VISIBLE);
        b.setOnClickListener(e ->{
            openMediaPlayer();
        });
        return builder.create();
    }

    private void openMediaPlayer() {
        Intent i = new Intent(context, Media.class);
        startActivityForResult(i, 0);
    }
}
