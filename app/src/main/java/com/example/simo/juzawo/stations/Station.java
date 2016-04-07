package com.example.simo.juzawo.stations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Station {


    @SerializedName("id")
    @Expose
    private Integer id;
    private String location;
    private String brand_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBranchName() {
        return brand_name;
    }

    public String setBranchName(String branchName) {
        this.brand_name = branchName;
        return branchName;
    }

}