package com.example.simo.juzawo.stations;

import com.example.simo.juzawo.stationdetails.StationDetails;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface StationsEndpoint {

    @GET("/stations")
    Call<List<Station>> findNearestStations(@Query("lat") double lat, @Query("long") double lng, @Query("limit") int limit);

    @GET("/stations/{id}")
    Call<StationDetails> findStation(@Path("id") long id);


}
