package com.example.simo.juzawo.stationdetails;

import com.example.simo.juzawo.prices.Price;
import com.example.simo.juzawo.user.User;

import java.util.List;

public class StationDetails {


    private int id;

    private String brand_name;

    private String location;

    private String tags;

    private String lat;

    private String lng;

    private String user_id;

    private User user;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    private String logo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public double getLat() {
        return Double.parseDouble(lat);
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public double getLng() {
        return Double.parseDouble(lng);
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    private List<Price> prices;

}