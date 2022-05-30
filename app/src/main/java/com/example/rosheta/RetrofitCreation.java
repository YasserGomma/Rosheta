package com.example.rosheta;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreation {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://roshta.systems/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static EndPoints Api = null;

    private RetrofitCreation() {
    }

    public static EndPoints getInstance() {
        return (Api == null) ? Api = retrofit.create(EndPoints.class) : Api;
    }

}
