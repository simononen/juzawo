package com.example.simo.juzawo.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StationsModel {
    public String location;
    public String branchName;
    //public char[] branchName;
    // Constructor to convert JSON object into a Java class instance
    public StationsModel(JSONObject object){
        try {
            this.branchName = object.getString("branch_name");
            this.location = object.getString("location");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Factory method to convert an array of JSON objects into a list of objects

    // User.fromJson(jsonArray);

    public static ArrayList<StationsModel> fromJson(JSONArray jsonObjects) {
        ArrayList<StationsModel> stations = new ArrayList<StationsModel>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                stations.add(new StationsModel(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return stations;
    }
}