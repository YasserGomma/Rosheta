package com.example.rosheta.views.pages.c_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.rosheta.R;
import com.example.rosheta.data.models.remote.AllDiseases;
import com.example.rosheta.data.models.remote.AllMedicines;
import com.example.rosheta.interfaces.EndPoints;
import com.example.rosheta.views.adapters.AllDiseasesAdapter;
import com.example.rosheta.views.adapters.AllMedicinesAdapter;
import com.example.rosheta.views.networking.RetrofitCreation;
import com.example.rosheta.views.pages.b_account.Login;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiseasesUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases_user);
        RecyclerView recyclerView = findViewById(R.id.rv_diseases);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        EndPoints Api = RetrofitCreation.getInstance();
        ArrayList<AllDiseases> diseases = new ArrayList<>();
        Call<ArrayList<AllDiseases>> call = Api.getAllDiseases(Login.user.getId()+"","");
        call.enqueue(new Callback<ArrayList<AllDiseases>>() {
            @Override
            public void onResponse(Call<ArrayList<AllDiseases>> call, Response<ArrayList<AllDiseases>> response) {
                for (int i = 0; i < response.body().size(); i++)
                    diseases.add(response.body().get(i));
                Log.e("med",response.body().toString());
                AllDiseasesAdapter medicinesAdapter = new AllDiseasesAdapter(DiseasesUser.this, diseases);
                recyclerView.setAdapter(medicinesAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onFailure(Call<ArrayList<AllDiseases>> call, Throwable t) {

                Log.e("med",t.getMessage().toString());
            }
        });


        EditText diseases_search=findViewById(R.id.diseases_search);
        diseases_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArrayList<AllDiseases> diseases = new ArrayList<>();
                Call<ArrayList<AllDiseases>> call = Api.getAllDiseases(Login.user.getId()+"",charSequence.toString());
                call.enqueue(new Callback<ArrayList<AllDiseases>>() {
                    @Override
                    public void onResponse(Call<ArrayList<AllDiseases>> call, Response<ArrayList<AllDiseases>> response) {
                        for (int i = 0; i < response.body().size(); i++)
                            diseases.add(response.body().get(i));
                        Log.e("med",response.body().toString());
                        AllDiseasesAdapter medicinesAdapter = new AllDiseasesAdapter(DiseasesUser.this, diseases);
                        recyclerView.setAdapter(medicinesAdapter);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<AllDiseases>> call, Throwable t) {

                        Log.e("med",t.getMessage().toString());
                    }
                });

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}