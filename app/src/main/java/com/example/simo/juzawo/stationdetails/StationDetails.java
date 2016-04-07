package com.example.simo.juzawo.stationdetails;

import com.example.simo.juzawo.prices.Prices;
import com.example.simo.juzawo.user.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class StationDetails {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("prices")
    @Expose
    private List<Prices> prices = new ArrayList<Prices>();

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * @param brandName The brand_name
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * @return The location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return The tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags The tags
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * @return The lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * @param lat The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * @return The lng
     */
    public String getLng() {
        return lng;
    }

    /**
     * @param lng The lng
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

    /**
     * @return The userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId The user_id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return The user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return The prices
     */
    public List<Prices> getPrices() {
        return prices;
    }

    /**
     * @param prices The prices
     */
    public void setPrices(List<Prices> prices) {
        this.prices = prices;
    }
}
