package com.example.simo.juzawo.stations;

import com.example.simo.juzawo.user.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StationsEndpoint {

    @GET("/stations")
    Call<List<Station>> findNearestStations(@Query("lat") double lat, @Query("long") double lng, @Query("limit") int limit);

    @GET("/stations/{id}")
    Call<Station> findStation(@Path("id") long id);

    @GET("/stations/user/{id}")
    Call<User> findUser(@Path("id") long id);


}
