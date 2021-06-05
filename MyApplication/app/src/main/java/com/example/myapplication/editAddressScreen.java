package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class editAddressScreen extends AppCompatActivity {
    public EditText nickname;
    public EditText name;
    public EditText addressLine1;
    public EditText addressLine2;
    public EditText addressLine3;
    public EditText city;
    public EditText postCode;
    public EditText contactNumber;
    public Button saveChanges;
    private Address b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nickname = findViewById(R.id.editAddressNickname);
        name = findViewById(R.id.editName);
        addressLine1 = findViewById(R.id.editAddressLine1);
        addressLine2 = findViewById(R.id.editAddressLine2);
        addressLine3 = findViewById(R.id.editAddressLine3);
        city = findViewById(R.id.editCity);
        postCode = findViewById(R.id.editPostcode);
        contactNumber = findViewById(R.id.editContactNumber);
        saveChanges = findViewById(R.id.saveChangesButton);


        for(Address a: store.addresses){
            if(a.currentlyEditing){
                nickname.setText(a.addressNickname);
                name.setText(a.name);
                addressLine1.setText(a.addressLineOne);
                addressLine2.setText(a.addressLineTwo);
                addressLine3.setText(a.addressLineThree);
                city.setText(a.city);
                postCode.setText(a.postcode);
                contactNumber.setText(a.phoneNumber);
                b = a;
                saveChanges.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        b.addressNickname = nickname.getText().toString();
                        b.name = name.getText().toString();
                        b.addressLineOne = addressLine1.getText().toString();
                        b.addressLineTwo = addressLine2.getText().toString();
                        b.addressLineThree = addressLine3.getText().toString();
                        b.city = city.getText().toString();
                        b.postcode = postCode.getText().toString();
                        b.phoneNumber = contactNumber.getText().toString();
                        b.currentlyEditing = false;

                        Intent intent = new Intent(view.getContext(), SavedAddressesScreen.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivityForResult(intent, 0);

                    }
            });
        }

        }
    }
}
