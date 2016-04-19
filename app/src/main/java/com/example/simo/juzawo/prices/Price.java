package com.example.simo.juzawo.prices;


import com.example.simo.juzawo.FuelType.FuelType;

public class Price {



    private Integer id;


    private String fuel_id;

    private String station_id;

    private String price;

    private FuelType fuel_type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFuel_id() {
        return fuel_id;
    }

    public void setFuel_id(String fuel_id) {
        this.fuel_id = fuel_id;
    }

    public String getStation_id() {
        return station_id;
    }

    public void setStation_id(String station_id) {
        this.station_id = station_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public FuelType getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(FuelType fuel_type) {
        this.fuel_type = fuel_type;
    }




}