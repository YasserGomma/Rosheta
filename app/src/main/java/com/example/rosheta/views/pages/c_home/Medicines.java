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
import android.widget.Toast;

import com.example.rosheta.R;
import com.example.rosheta.data.source.remote.AllMedicines;
import com.example.rosheta.interfaces.EndPoints;
import com.example.rosheta.views.adapters.AllMedicinesAdapter;
import com.example.rosheta.views.adapters.ExaminationsAdapter;
import com.example.rosheta.views.adapters.MedicinesAdapter;
import com.example.rosheta.views.networking.RetrofitCreation;
import com.example.rosheta.views.pages.b_account.Login;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Medicines extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);
        RecyclerView recyclerView = findViewById(R.id.rv_medicines);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        EndPoints Api = RetrofitCreation.getInstance();
        ArrayList<AllMedicines> medicines = new ArrayList<>();
        Call<ArrayList<AllMedicines>> call = Api.getAllMedicines(Login.user.getId()+"","");
        call.enqueue(new Callback<ArrayList<AllMedicines>>() {
            @Override
            public void onResponse(Call<ArrayList<AllMedicines>> call, Response<ArrayList<AllMedicines>> response) {
                for (int i = 0; i < response.body().size(); i++)
                    medicines.add(response.body().get(i));
                Log.e("med",response.body().toString());
                AllMedicinesAdapter medicinesAdapter = new AllMedicinesAdapter(Medicines.this, medicines);
                recyclerView.setAdapter(medicinesAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onFailure(Call<ArrayList<AllMedicines>> call, Throwable t) {

                Log.e("med",t.getMessage().toString());
            }
        });


        EditText medicines_search=findViewById(R.id.medicines_search);
        medicines_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArrayList<AllMedicines> medicines = new ArrayList<>();
                Call<ArrayList<AllMedicines>> call = Api.getAllMedicines(Login.user.getId()+"",charSequence.toString());
                call.enqueue(new Callback<ArrayList<AllMedicines>>() {
                    @Override
                    public void onResponse(Call<ArrayList<AllMedicines>> call, Response<ArrayList<AllMedicines>> response) {
                        for (int i = 0; i < response.body().size(); i++)
                            medicines.add(response.body().get(i));
                        Log.e("med",response.body().toString());
                        AllMedicinesAdapter medicinesAdapter = new AllMedicinesAdapter(Medicines.this, medicines);
                        medicinesAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(medicinesAdapter);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<AllMedicines>> call, Throwable t) {

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