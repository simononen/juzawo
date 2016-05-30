package com.example.simo.juzawo.prices;


import com.example.simo.juzawo.FuelType.FuelType;

public class Price {

    private Integer id;
    private String fuelId;
    private String stationId;
    private String price;
    private String createdAt;
    private String updatedAt;
    private FuelType fuelType;

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFuelId() {
        return fuelId;
    }

    public void setFuelId(String fuelId) {
        this.fuelId = fuelId;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }


    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }
}