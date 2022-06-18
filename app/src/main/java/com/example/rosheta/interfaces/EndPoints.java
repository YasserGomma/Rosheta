package com.example.rosheta.interfaces;

import com.example.rosheta.data.source.remote.Examination;
import com.example.rosheta.data.source.remote.Medicines;
import com.example.rosheta.data.source.remote.User;
import com.example.rosheta.data.source.remote.UserR;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface EndPoints {


    @FormUrlEncoded
    @POST("login")
    Call<User> login(@Field("email") String email, @Field("password") String password);


    @POST("register")
    Call<User> register(@Body UserR user);

    @FormUrlEncoded
    @POST("patient/examinations")
    Call<ArrayList<Examination>> getExaminations(@Field("user_id") String user_id );


    @FormUrlEncoded
    @POST("examination/medicines")
    Call<ArrayList<Medicines>> getMedicines(@Field("examination_id") String examination_id );




}
