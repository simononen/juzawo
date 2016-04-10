package com.example.simo.juzawo.stations;

public class Station {


    private Integer id;
    private String location;
    private String brand_name;
    private String logo;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

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