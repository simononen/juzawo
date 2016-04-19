package com.example.simo.juzawo.http;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static Retrofit ourInstance = null;

    public static Retrofit build() {
        if (ourInstance == null) {
            ourInstance = new Retrofit
                    .Builder()//http://192.168.43.218:8080
                    .baseUrl("http://192.168.43.70:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return ourInstance;
    }


}
