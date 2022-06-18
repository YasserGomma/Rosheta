package com.example.rosheta.views.networking;

import com.example.rosheta.interfaces.EndPoints;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitCreation {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://roshta.systems/api/")
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                    .setLenient()
                    .create()))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();

    private static EndPoints Api = null;

    private RetrofitCreation() {
    }

    public static EndPoints getInstance() {
        return (Api == null) ? Api = retrofit.create(EndPoints.class) : Api;
    }

}
