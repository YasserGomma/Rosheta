package com.example.rosheta;

import java.util.ArrayList;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface EndPoints {

    @FormUrlEncoded
    @POST("/api/login")
    Call<User> login(@Field("email") String email, @Field("password") String password);




}
