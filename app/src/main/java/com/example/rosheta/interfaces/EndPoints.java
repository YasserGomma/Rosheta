package com.example.rosheta.interfaces;

import com.example.rosheta.data.source.remote.AllMedicines;
import com.example.rosheta.data.source.remote.Clinc;
import com.example.rosheta.data.source.remote.Examination;
import com.example.rosheta.data.source.remote.ExaminationRequest;
import com.example.rosheta.data.source.remote.Medicines;
import com.example.rosheta.data.source.remote.Pharmacy;
import com.example.rosheta.data.source.remote.Report;
import com.example.rosheta.data.source.remote.User;
import com.example.rosheta.data.source.remote.UserR;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface EndPoints {


    @FormUrlEncoded
    @POST("login")
    Call<User> login(@Field("email") String email, @Field("password") String password);


    @POST("register")
    Call<User> register(@Body UserR user);

    @FormUrlEncoded
    @POST("patient/examinations")
    Call<ArrayList<Examination>> getExaminations(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("examinations/medicines")
    Call<ArrayList<Medicines>> getMedicines(@Field("examination_id") String examination_id);

    @FormUrlEncoded
    @POST("examinations/diseases")
    Call<ArrayList<Report>> getReports(@Field("examination_id") String examination_id);

    @FormUrlEncoded
    @POST("examinationRequests")
    Call<ArrayList<ExaminationRequest>> getExaminationRequest(@Field("user_id") String user_id, @Field("search") String search);

    @FormUrlEncoded
    @POST("search")
    Call<ArrayList<Clinc>> getClinic(@Field("type") String type, @Field("search") String search);

    @FormUrlEncoded
    @POST("search")
    Call<ArrayList<Pharmacy>> getPharmacy(@Field("type") String type, @Field("search") String search);

    @FormUrlEncoded
    @POST("examinations/reserve")
    Call<ExaminationRequest> reserveExamination(@Field("user_id") String user_id, @Field("location_id") String location_id);

    @FormUrlEncoded
    @POST("user/medicines")
    Call<ArrayList<AllMedicines>> getAllMedicines(@Field("user_id") String user_id, @Field("search") String search);

}
