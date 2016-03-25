package com.example.simo.juzawo.http.services;

import com.example.simo.juzawo.models.DocType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface SharepointService {

    @GET("/documents")
    public Call<List<DocType>> listDoctypes();
}
