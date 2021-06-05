package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SavedAddressesScreen extends AppCompatActivity {
    public Intent i;
    public Address address;
    public TextView nickname;
    public TextView nameView;
    public TextView addressLine1;
    public TextView addressLine2;
    public TextView addressLine3;
    public TextView city;
    public TextView postCode;
    public TextView phoneNumber;
    public TextView nickname2;
    public TextView nameView2;
    public TextView addressLine1Two;
    public TextView addressLine2Two;
    public TextView addressLine3Two;
    public TextView city2;
    public TextView postCode2;
    public TextView phoneNumber2;
    public RelativeLayout address1Container;
    public RelativeLayout address2Container;
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pref = getPreferences(Context.MODE_PRIVATE);
        editor = pref.edit();
        //editor.clear();
        //editor.commit();

        final Button addNewAddress = findViewById(R.id.addNewAddress);


        addNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), addNewAddressScreen.class);
                startActivityForResult(intent, 0);
            }

        });

        Log.d("Store", store.addresses.size() + "");
        //Intent for pulling data from other classes
        AsyncTask restaurants = new loadRestaurants().execute();
        //Intent for pulling data from other classes
        i = getIntent();
        nickname = findViewById(R.id.addressNickname);
        nameView = findViewById(R.id.nameText);
        addressLine1 = findViewById(R.id.address1);
        addressLine2 = findViewById(R.id.address2);
        addressLine3 = findViewById(R.id.address3);
        city = findViewById(R.id.cityText);
        postCode = findViewById(R.id.postCodeText);
        phoneNumber = findViewById(R.id.contactNumberText);
        address1Container = findViewById(R.id.address1Container);
        nickname2 = findViewById(R.id.addressNickname2);
        nameView2 = findViewById(R.id.nameText2);
        addressLine1Two = findViewById(R.id.address1Two);
        addressLine2Two = findViewById(R.id.address2Two);
        addressLine3Two = findViewById(R.id.address3Two);
        city2 = findViewById(R.id.cityText2);
        postCode2 = findViewById(R.id.postCodeText2);
        phoneNumber2 = findViewById(R.id.contactNumberText2);
        address2Container = findViewById(R.id.address2Container);
        final Button selectAddress1 = findViewById(R.id.selectAddress1);
        final Button selectAddress2 = findViewById(R.id.selectAddress2);
        selectAddress1.setVisibility(View.INVISIBLE);
        selectAddress2.setVisibility(View.INVISIBLE);

        if (pref.contains("nickname1")) {
            Address temp1 = new Address(pref.getString("nickname1", "DEFAULT"), pref.getString("name1", "DEFAULT"), pref.getString("addressLineOne1", "DEFAULT"), pref.getString("addressLineTwo1", "DEFAULT"), pref.getString("addressLineThree1", "DEFAULT"), pref.getString("city1", "DEFAULT"), pref.getString("postcode1", "DEFAULT"), pref.getString("phoneNumber1", "DEFAULT"));
            store.addresses.add(temp1);
            if (store.addresses.size() >= 1) {
                nickname.setText(store.addresses.get(0).addressNickname);
                nickname.setTextSize(20);
                nickname.setTextColor(Color.BLACK);
                nameView.setText(store.addresses.get(0).name);
                nameView.setTextSize(16);
                addressLine1.setText(store.addresses.get(0).addressLineOne);
                addressLine1.setTextSize(16);
                addressLine2.setText(store.addresses.get(0).addressLineTwo);
                addressLine2.setTextSize(16);
                addressLine3.setText(store.addresses.get(0).addressLineThree);
                addressLine3.setTextSize(16);
                city.setText(store.addresses.get(0).city);
                city.setTextSize(16);
                postCode.setText(store.addresses.get(0).postcode);
                postCode.setTextSize(16);
                phoneNumber.setText(store.addresses.get(0).phoneNumber);
                phoneNumber.setTextSize(16);
                selectAddress1.setVisibility(View.VISIBLE);
                
                selectAddress1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (store.addresses.size() >= 1) {
                            if (!store.addresses.get(0).selectedAddress) {
                                store.addresses.get(0).selectedAddress = true;
                                if (store.addresses.size() >= 2) {
                                    store.addresses.get(1).selectedAddress = false;
                                }
                            }
                        }
                        Intent i = new Intent(view.getContext(), restaurants.class);
                        startActivity(i);
                    }


                });
                address1Container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        store.addresses.get(0).currentlyEditing = true;
                        Log.d("Selected", "" + store.addresses.get(0).currentlyEditing);
                        if (store.addresses.size() >= 2) {
                            // Make sure that no other addresses are set as being edited
                            if (store.addresses.get(1).currentlyEditing) {
                                store.addresses.get(1).currentlyEditing = false;
                            }

                        } else {
                            //Do nothing
                        }
                        Intent intent = new Intent(view.getContext(), editAddressScreen.class);
                        startActivityForResult(intent, 0);

                    }
                });
            }
        }
        if (pref.contains("nickname2")) {
            Address temp2 = new Address(pref.getString("nickname2", "DEFAULT"), pref.getString("name2", "DEFAULT"), pref.getString("addressLineOne2", "DEFAULT"), pref.getString("addressLineTwo2", "DEFAULT"), pref.getString("addressLineThree2", "DEFAULT"), pref.getString("city2", "DEFAULT"), pref.getString("postcode2", "DEFAULT"), pref.getString("phoneNumber2", "DEFAULT"));
            store.addresses.add(store.addresses.size(), temp2);
            if (store.addresses.size() >= 2) {
                nickname2.setText(store.addresses.get(1).addressNickname);
                nickname2.setTextSize(20);
                nameView2.setText(store.addresses.get(1).name);
                nameView2.setTextSize(16);
                addressLine1Two.setText(store.addresses.get(1).addressLineOne);
                addressLine1Two.setTextSize(16);
                addressLine2Two.setText(store.addresses.get(1).addressLineTwo);
                addressLine2Two.setTextSize(16);
                addressLine3Two.setText(store.addresses.get(1).addressLineThree);
                addressLine3Two.setTextSize(16);
                city2.setText(store.addresses.get(1).city);
                city2.setTextSize(16);
                postCode2.setText(store.addresses.get(1).postcode);
                postCode2.setTextSize(16);
                phoneNumber2.setText(store.addresses.get(1).phoneNumber);
                phoneNumber2.setTextSize(16);
                selectAddress2.setVisibility(View.VISIBLE);


                selectAddress2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (store.addresses.size() >= 2) {
                            if (!store.addresses.get(1).selectedAddress) {
                                store.addresses.get(1).selectedAddress = true;
                                store.addresses.get(0).selectedAddress = false;
                                nickname.setTextColor(Color.BLACK);
                                nickname2.setTextColor(Color.GRAY);


                            }



                        }
                        Intent i = new Intent(view.getContext(), restaurants.class);
                        startActivity(i);
                    }

                });

                address2Container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        store.addresses.get(1).currentlyEditing = true;
                        store.addresses.get(0).currentlyEditing = false;
                        Intent intent = new Intent(view.getContext(), editAddressScreen.class);
                        startActivityForResult(intent, 0);

                    }
                });

            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Store", store.addresses.size() + "");

        final Button selectAddress1 = findViewById(R.id.selectAddress1);
        final Button selectAddress2 = findViewById(R.id.selectAddress2);

        selectAddress1.setVisibility(View.INVISIBLE);
        selectAddress2.setVisibility(View.INVISIBLE);

        if (store.addresses.size() >= 1) {
            nickname.setText(store.addresses.get(0).addressNickname);
            nickname.setTextSize(20);
            nickname.setTextColor(Color.BLACK);
            nameView.setText(store.addresses.get(0).name);
            nameView.setTextSize(16);
            addressLine1.setText(store.addresses.get(0).addressLineOne);
            addressLine1.setTextSize(16);
            addressLine2.setText(store.addresses.get(0).addressLineTwo);
            addressLine2.setTextSize(16);
            addressLine3.setText(store.addresses.get(0).addressLineThree);
            addressLine3.setTextSize(16);
            city.setText(store.addresses.get(0).city);
            city.setTextSize(16);
            postCode.setText(store.addresses.get(0).postcode);
            postCode.setTextSize(16);
            phoneNumber.setText(store.addresses.get(0).phoneNumber);
            phoneNumber.setTextSize(16);

            editor.putString("nickname1", store.addresses.get(0).addressNickname);
            editor.putString("name1", store.addresses.get(0).name);
            editor.putString("addressLineOne1", store.addresses.get(0).addressLineOne);
            editor.putString("addressLineTwo1", store.addresses.get(0).addressLineTwo);
            editor.putString("addressLineThree1", store.addresses.get(0).addressLineThree);
            editor.putString("city1", store.addresses.get(0).city);
            editor.putString("postcode1", store.addresses.get(0).postcode);
            editor.putString("phoneNumber1", store.addresses.get(0).phoneNumber);
            editor.commit();


            selectAddress1.setVisibility(View.VISIBLE);

            selectAddress1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (store.addresses.size() >= 1) {
                        if (!store.addresses.get(0).selectedAddress) {
                            store.addresses.get(0).selectedAddress = true;
                            if (store.addresses.size() >= 2) {
                                store.addresses.get(1).selectedAddress = false;
                            }
                        }


                    }
                    Intent i = new Intent(view.getContext(), restaurants.class);
                    startActivity(i);
                }


            });

            //Intent to go to edit the address when clicked.
            address1Container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    store.addresses.get(0).currentlyEditing = true;
                    Log.d("Selected", "" + store.addresses.get(0).currentlyEditing);
                    if (store.addresses.size() >= 2) {
                        // Make sure that no other addresses are set as being edited
                        if (store.addresses.get(1).currentlyEditing) {
                            store.addresses.get(1).currentlyEditing = false;
                        }

                    } else {
                        //Do nothing
                    }
                    Intent intent = new Intent(view.getContext(), editAddressScreen.class);
                    startActivityForResult(intent, 0);

                }
            });
            if (store.addresses.size() >= 2) {
                nickname2.setText(store.addresses.get(1).addressNickname);
                nickname2.setTextSize(20);
                nameView2.setText(store.addresses.get(1).name);
                nameView2.setTextSize(16);
                addressLine1Two.setText(store.addresses.get(1).addressLineOne);
                addressLine1Two.setTextSize(16);
                addressLine2Two.setText(store.addresses.get(1).addressLineTwo);
                addressLine2Two.setTextSize(16);
                addressLine3Two.setText(store.addresses.get(1).addressLineThree);
                addressLine3Two.setTextSize(16);
                city2.setText(store.addresses.get(1).city);
                city2.setTextSize(16);
                postCode2.setText(store.addresses.get(1).postcode);
                postCode2.setTextSize(16);
                phoneNumber2.setText(store.addresses.get(1).phoneNumber);
                phoneNumber2.setTextSize(16);

                selectAddress2.setVisibility(View.VISIBLE);

                editor.putString("nickname2", store.addresses.get(1).addressNickname);
                editor.putString("name2", store.addresses.get(1).name);
                editor.putString("addressLineOne2", store.addresses.get(1).addressLineOne);
                editor.putString("addressLineTwo2", store.addresses.get(1).addressLineTwo);
                editor.putString("addressLineThree2", store.addresses.get(1).addressLineThree);
                editor.putString("city2", store.addresses.get(1).city);
                editor.putString("postcode2", store.addresses.get(1).postcode);
                editor.putString("phoneNumber2", store.addresses.get(1).phoneNumber);
                editor.commit();
                selectAddress2.setVisibility(View.VISIBLE);

                selectAddress2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (store.addresses.size() >= 2) {

                            if (!store.addresses.get(1).selectedAddress) {
                                store.addresses.get(1).selectedAddress = true;
                                store.addresses.get(0).selectedAddress = false;
                                nickname.setTextColor(Color.BLACK);
                                nickname2.setTextColor(Color.GRAY);
                            }


                        } Intent i = new Intent(view.getContext(), restaurants.class);
                        startActivity(i);
                    }

                });

                address2Container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        store.addresses.get(1).currentlyEditing = true;
                        store.addresses.get(0).currentlyEditing = false;
                        Intent intent = new Intent(view.getContext(), editAddressScreen.class);
                        startActivityForResult(intent, 0);

                    }
                });


            }

        }
    }
}

