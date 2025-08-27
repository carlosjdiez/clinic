package com.example.clinic.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClinicApi {
    public static ClinicApiInterface buildInstance() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8081/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ClinicApiInterface.class);
    }
}
