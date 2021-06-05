package com.example.myapplication;

/* A simple address object to hold infomation about a customers delivery location */

import java.io.Serializable;

public class Address implements Serializable {
    public String addressNickname;
    public String name;
    public String addressLineOne;
    public String addressLineTwo;
    public String addressLineThree;
    public String city;
    public String postcode;
    public String phoneNumber;
    public boolean currentlyEditing;
    public boolean selectedAddress;

    public Address(String addressNickname, String name, String addressLineOne, String addressLineTwo, String addressLineThree, String city, String postcode, String phoneNumber) {
        this.addressNickname = addressNickname;
        this.name = name;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.addressLineThree = addressLineThree;
        this.city = city;
        this.postcode = postcode;
        this.phoneNumber = phoneNumber;

    }
}
