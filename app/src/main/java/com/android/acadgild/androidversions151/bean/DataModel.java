package com.android.acadgild.androidversions151.bean;

/**
 * Created by Jal on 18-09-2017.
 * DataModel class with Get & Set methods
 */

public class DataModel {

    String name;
    int id_;

    public DataModel(String name,int id_) {
        this.name = name;
        this.id_ = id_;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id_;
    }

    public void setId(int id_) {
        this.id_ = id_;
    }


}
