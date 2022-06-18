package com.example.rosheta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.rosheta.data.source.remote.Examination;
import com.example.rosheta.data.source.remote.Medicines;
import com.example.rosheta.interfaces.EndPoints;
import com.example.rosheta.views.adapters.ExaminationsAdapter;
import com.example.rosheta.views.adapters.MedicinesAdapter;
import com.example.rosheta.views.networking.RetrofitCreation;
import com.example.rosheta.views.pages.c_home.Examinations;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExaminationDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination_details);
        TextView tv_examination_details_doc_name,tv_examination_details_id,
                tv_examination_details_date,tv_examination_details_doc_sp,
                tv_examination_details_report,
                tv_examination_details_notes;


        tv_examination_details_doc_name=findViewById(R.id.tv_examination_details_doc_name);
        tv_examination_details_id=findViewById(R.id.tv_examination_details_id);
        tv_examination_details_date=findViewById(R.id.tv_examination_details_date);
        tv_examination_details_doc_sp=findViewById(R.id.tv_examination_details_doc_sp);
        tv_examination_details_report=findViewById(R.id.tv_examination_details_report);
        tv_examination_details_notes=findViewById(R.id.tv_examination_details_notes);


        RecyclerView recyclerView=findViewById(R.id.rv_examination_details_medicines);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        Examination examination=ExaminationsAdapter.examination;

        tv_examination_details_doc_name.setText(examination.getDoctor_name());
        tv_examination_details_id.setText("Prescription id : "+examination.getId()+"");
        tv_examination_details_date.setText(examination.getCreated_at()+"");
        tv_examination_details_report.setText(examination.getReport());



        EndPoints Api = RetrofitCreation.getInstance();
        ArrayList<Medicines> medicines = new ArrayList<>();
        Call<ArrayList<Medicines>> call = Api.getMedicines(ExaminationsAdapter.examinationID_Adapter);
        call.enqueue(new Callback<ArrayList<Medicines>>() {
            @Override
            public void onResponse(Call<ArrayList<Medicines>> call, Response<ArrayList<Medicines>> response) {
                for(int i=0;i<response.body().size();i++)
                    medicines.add(response.body().get(i));

                MedicinesAdapter medicinesAdapter = new MedicinesAdapter(ExaminationDetails.this, medicines);
                recyclerView.setAdapter(medicinesAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }
            @Override
            public void onFailure(Call<ArrayList<Medicines>> call, Throwable t) {

            }
        });

    }
}