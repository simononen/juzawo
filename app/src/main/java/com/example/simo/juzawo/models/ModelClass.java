package com.example.simo.juzawo.models;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("Stations")
public class ModelClass extends ParseObject {

    public String getText() {
        return getString("name");
    }

    public void setText(String value) {
        put("name", value);
    }

    public String getPlace() {
        return getString("brand");
    }

    public void setPlace(String value) {
        put("name", value);
    }


    public ParseGeoPoint getLocation() {
        return getParseGeoPoint("location");
    }

    public static ParseQuery<ModelClass> getQuery() {
        return ParseQuery.getQuery(ModelClass.class);
    }


}