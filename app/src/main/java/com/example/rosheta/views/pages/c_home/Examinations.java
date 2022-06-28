package com.example.rosheta.views.pages.c_home;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.rosheta.R;
import com.example.rosheta.data.models.remote.Examination;
import com.example.rosheta.interfaces.EndPoints;
import com.example.rosheta.views.adapters.ExaminationsAdapter;
import com.example.rosheta.views.networking.RetrofitCreation;
import com.example.rosheta.views.pages.b_account.Login;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class Examinations extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examinations);
        RecyclerView recyclerView = findViewById(R.id.rv_examinations);
        StaggeredGridLayoutManager layoutManager
                = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        EndPoints Api = RetrofitCreation.getInstance();
        ArrayList<Examination> examinations = new ArrayList<>();
        Call<ArrayList<Examination>> call = Api.getExaminations(Login.user.id + "");
        call.enqueue(new Callback<ArrayList<Examination>>() {
            @Override
            public void onResponse(Call<ArrayList<Examination>> call, Response<ArrayList<Examination>> response) {
                examinations.add(new Examination());
                Log.e("id",Login.user.id+"");
                for (int i = 0; i < response.body().size(); i++)
                    examinations.add(response.body().get(i));
                ExaminationsAdapter examinationsAdapter = new ExaminationsAdapter(Examinations.this, examinations);
                recyclerView.setAdapter(examinationsAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }
            @Override
            public void onFailure(Call<ArrayList<Examination>> call, Throwable t) {
            }
        });
    }
}