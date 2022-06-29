package com.example.rosheta.interfaces;

import com.example.rosheta.data.models.remote.AllDiseases;
import com.example.rosheta.data.models.remote.AllMedicines;
import com.example.rosheta.data.models.remote.Clinc;
import com.example.rosheta.data.models.remote.Examination;
import com.example.rosheta.data.models.remote.ExaminationRequest;
import com.example.rosheta.data.models.remote.Medicines;
import com.example.rosheta.data.models.remote.Pharmacy;
import com.example.rosheta.data.models.remote.Report;
import com.example.rosheta.data.models.remote.User;
import com.example.rosheta.data.models.remote.UserR;

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
    @FormUrlEncoded
    @POST("user/diseases")
    Call<ArrayList<AllDiseases>> getAllDiseases(@Field("user_id") String user_id, @Field("search") String search);
}
