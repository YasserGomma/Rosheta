package com.example.rosheta.views.pages.c_home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.rosheta.R;
import com.example.rosheta.data.models.remote.ExaminationRequest;
import com.example.rosheta.interfaces.EndPoints;
import com.example.rosheta.views.adapters.ExaminationRequestAdapter;
import com.example.rosheta.views.networking.RetrofitCreation;
import com.example.rosheta.views.pages.b_account.Login;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExaminationRequests extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination_requests);
        RecyclerView recyclerView = findViewById(R.id.rv_examinations_request);
        StaggeredGridLayoutManager layoutManager
                = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        EndPoints Api = RetrofitCreation.getInstance();
        ArrayList<ExaminationRequest> examinations = new ArrayList<>();
        Call<ArrayList<ExaminationRequest>> call = Api.getExaminationRequest(Login.user.id+"", "");
        call.enqueue(new Callback<ArrayList<ExaminationRequest>>() {
            @Override
            public void onResponse(Call<ArrayList<ExaminationRequest>> call, Response<ArrayList<ExaminationRequest>> response) {
                examinations.clear();
                examinations.add(new ExaminationRequest());
                for (int i = 0; i < response.body().size(); i++)
                    examinations.add(response.body().get(i));
                ExaminationRequestAdapter examinationsAdapter = new ExaminationRequestAdapter(ExaminationRequests.this, examinations);
                examinationsAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(examinationsAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onFailure(Call<ArrayList<ExaminationRequest>> call, Throwable t) {

            }
        });
        EditText et_examinations_request_search = findViewById(R.id.et_examinations_request_search);
        et_examinations_request_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArrayList<ExaminationRequest> examinations = new ArrayList<>();
                Call<ArrayList<ExaminationRequest>> call = Api.getExaminationRequest(Login.user.id + "", charSequence.toString());
                call.enqueue(new Callback<ArrayList<ExaminationRequest>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ExaminationRequest>> call, Response<ArrayList<ExaminationRequest>> response) {
                        examinations.clear();
                        examinations.add(new ExaminationRequest());
                        for (int i = 0; i < response.body().size(); i++)
                            examinations.add(response.body().get(i));
                        ExaminationRequestAdapter examinationsAdapter = new ExaminationRequestAdapter(ExaminationRequests.this, examinations);
                        examinationsAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(examinationsAdapter);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ExaminationRequest>> call, Throwable t) {

                    }
                });

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}