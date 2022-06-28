package com.example.rosheta.views.pages.c_home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rosheta.views.pages.b_account.BuyRosheta;
import com.example.rosheta.R;
import com.example.rosheta.data.models.remote.Examination;
import com.example.rosheta.data.models.remote.Medicines;
import com.example.rosheta.data.models.remote.Report;
import com.example.rosheta.interfaces.EndPoints;
import com.example.rosheta.views.adapters.ExaminationsAdapter;
import com.example.rosheta.views.adapters.MedicinesAdapter;
import com.example.rosheta.views.adapters.ReportsAdapter;
import com.example.rosheta.views.networking.RetrofitCreation;
import com.example.rosheta.views.pages.parents.BaseActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExaminationDetails extends BaseActivity {
    public static ArrayList<Medicines> medicines = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination_details);
        TextView tv_examination_details_doc_name, tv_examination_details_id,
                tv_examination_details_date, tv_examination_details_doc_sp,
                tv_examination_details_notes;


        tv_examination_details_doc_name = findViewById(R.id.tv_examination_details_doc_name);
        tv_examination_details_id = findViewById(R.id.tv_examination_details_id);
        tv_examination_details_date = findViewById(R.id.tv_examination_details_date);
        tv_examination_details_doc_sp = findViewById(R.id.tv_examination_details_doc_sp);
        tv_examination_details_notes = findViewById(R.id.tv_examination_details_notes);


        Examination examination = ExaminationsAdapter.examination;

        tv_examination_details_doc_name.setText(examination.getDoctor_name());
        tv_examination_details_id.setText("Prescription id : " + examination.getId() + "");
        tv_examination_details_date.setText(examination.getCreated_at() + "");


        RecyclerView recyclerView = findViewById(R.id.rv_examination_details_medicines);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        EndPoints Api = RetrofitCreation.getInstance();
        Call<ArrayList<Medicines>> call = Api.getMedicines(ExaminationsAdapter.examinationID_Adapter);
        call.enqueue(new Callback<ArrayList<Medicines>>() {
            @Override
            public void onResponse(Call<ArrayList<Medicines>> call, Response<ArrayList<Medicines>> response) {
                for (int i = 0; i < response.body().size(); i++)
                    medicines.add(response.body().get(i));

                MedicinesAdapter medicinesAdapter = new MedicinesAdapter(ExaminationDetails.this, medicines);
                recyclerView.setAdapter(medicinesAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onFailure(Call<ArrayList<Medicines>> call, Throwable t) {

            }
        });

        RecyclerView recyclerView2 = findViewById(R.id.rv_examination_details_report);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        Call<ArrayList<Report>> call2 = Api.getReports(ExaminationsAdapter.examinationID_Adapter);
        ArrayList<Report> reports = new ArrayList<>();
        call2.enqueue(new Callback<ArrayList<Report>>() {
            @Override
            public void onResponse(Call<ArrayList<Report>> call, Response<ArrayList<Report>> response) {
                for (int i = 0; i < response.body().size(); i++)
                    reports.add(response.body().get(i));

                ReportsAdapter reportsAdapter = new ReportsAdapter(ExaminationDetails.this, reports);
                recyclerView2.setAdapter(reportsAdapter);
                recyclerView2.setItemAnimator(new DefaultItemAnimator());

            }

            @Override
            public void onFailure(Call<ArrayList<Report>> call, Throwable t) {

            }
        });


        Button btn_examination_details_buy=findViewById(R.id.btn_examination_details_buy);
        btn_examination_details_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_screen(ExaminationDetails.this, BuyRosheta.class);
            }
        });


    }
}