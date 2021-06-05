package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class addNewAddressScreen extends AppCompatActivity {
    public Address address;
    public sampleObject test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address_screen);



        Button addAddress = findViewById(R.id.addAddress);
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText addressNickname = findViewById(R.id.addressNicknameInput);
                EditText fullName = findViewById(R.id.nameInput);
                EditText addressLineOne = findViewById(R.id.addressLineOneInput);
                EditText addressLineTwo = findViewById(R.id.addressLineTwoInput);
                EditText addressLineThree = findViewById(R.id.addressLineThreeInput2);
                EditText city = findViewById(R.id.cityInput2);
                EditText postcodeInput = findViewById(R.id.postcodeInput2);
                EditText ContactNumber = findViewById(R.id.phoneNumberInput);

                if (address == null) {

                    if(TextUtils.isEmpty(addressNickname.getText())) {
                        Toast.makeText(getApplicationContext(), "Please enter a nickname",Toast.LENGTH_LONG).show();

                    }else if(TextUtils.isEmpty(fullName.getText())) {
                        Toast.makeText(getApplicationContext(), "Please enter a name",Toast.LENGTH_LONG).show();

                    }else if(TextUtils.isEmpty(addressLineOne.getText())) {
                        Toast.makeText(getApplicationContext(), "Please enter at least one line of your address",Toast.LENGTH_LONG).show();

                    }else if (TextUtils.isEmpty(city.getText())) {
                        Toast.makeText(getApplicationContext(), "Please enter a city",Toast.LENGTH_LONG).show();

                    }else if (TextUtils.isEmpty(postcodeInput.getText())) {
                        Toast.makeText(getApplicationContext(), "Please enter a postcode",Toast.LENGTH_LONG).show();

                    } else if (TextUtils.isEmpty(ContactNumber.getText())) {
                        Toast.makeText(getApplicationContext(), "Please enter a phone number",Toast.LENGTH_LONG).show();

                    } else {
                        address = new Address(addressNickname.getText().toString(), fullName.getText().toString(), addressLineOne.getText().toString(), addressLineTwo.getText().toString(), addressLineThree.getText().toString(), city.getText().toString(), postcodeInput.getText().toString(), ContactNumber.getText().toString());
                        Log.d("Address", address.name + " " + address.addressLineOne + " " + address.addressLineTwo + " " + address.addressLineThree + " " + address.city + " " + address.postcode + " " + address.phoneNumber);
                        store.addresses.add(store.addresses.size(), address);
                        Log.d("Saved", "Saved" + address.name);

                        Intent i = new Intent(getBaseContext(), SavedAddressesScreen.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivityForResult(i, 0);
                    }
                }

            }
        });


    }
}
