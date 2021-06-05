package com.example.myapplication;

import java.io.Serializable;

public class sampleObject implements Serializable {

    public int id;
    public String name;

    public sampleObject(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
