package com.example.rosheta.views.pages.c_home;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rosheta.R;
import com.example.rosheta.data.source.remote.Clinc;
import com.example.rosheta.data.source.remote.ExaminationRequest;
import com.example.rosheta.interfaces.EndPoints;
import com.example.rosheta.views.adapters.ClinicAdapter;
import com.example.rosheta.views.adapters.ExaminationRequestAdapter;
import com.example.rosheta.views.networking.RetrofitCreation;
import com.example.rosheta.views.pages.b_account.Login;
import com.example.rosheta.views.pages.parents.BaseActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClinicDetailes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_detailes);


        TextView clinicName,docName,address;
        clinicName=findViewById(R.id.clinic_details_name);
        docName=findViewById(R.id.clinic_details_doc_name);
        address=findViewById(R.id.clinic_details_add);

        Clinc clinic= ClinicAdapter.clinc;
        clinicName.setText(clinic.getLName());
        docName.setText("Owner:\n"+clinic.getName());
        address.setText(BaseActivity.getCompleteAddressString(this,Double.parseDouble(clinic.getLat()),Double.parseDouble(clinic.getLng())));

        Button reserve=findViewById(R.id.clinic_details_reserve);
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EndPoints Api = RetrofitCreation.getInstance();
                Call<ExaminationRequest> call = Api.reserveExamination(Login.user.getId()+"", clinic.getId()+"");
                call.enqueue(new Callback<ExaminationRequest>() {
                    @Override
                    public void onResponse(Call<ExaminationRequest> call, Response<ExaminationRequest> response) {
                        new AlertDialog.Builder(ClinicDetailes.this)
                                .setTitle("Confirmation")
                                .setMessage("You have been reserved an examination!")
                                .setPositiveButton("OK",null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }

                    @Override
                    public void onFailure(Call<ExaminationRequest> call, Throwable t) {
                        new AlertDialog.Builder(ClinicDetailes.this)
                                .setTitle("Error")
                                .setMessage("You have a pending reservation")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                });
            }
        });

        RecyclerView recyclerView = findViewById(R.id.rv_clinic_details);
        StaggeredGridLayoutManager layoutManager
                = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        EndPoints Api = RetrofitCreation.getInstance();
        ArrayList<ExaminationRequest> examinations = new ArrayList<>();
        Call<ArrayList<ExaminationRequest>> call = Api.getExaminationRequest(Login.user.id+"", clinic.getLName());
        call.enqueue(new Callback<ArrayList<ExaminationRequest>>() {
            @Override
            public void onResponse(Call<ArrayList<ExaminationRequest>> call, Response<ArrayList<ExaminationRequest>> response) {
                examinations.clear();
                examinations.add(new ExaminationRequest());
                for (int i = 0; i < response.body().size(); i++)
                    examinations.add(response.body().get(i));
                ExaminationRequestAdapter examinationsAdapter = new ExaminationRequestAdapter(ClinicDetailes.this, examinations);
                examinationsAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(examinationsAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }
            @Override
            public void onFailure(Call<ArrayList<ExaminationRequest>> call, Throwable t) {

            }
        });

    }
}