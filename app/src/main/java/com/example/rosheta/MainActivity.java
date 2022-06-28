package com.example.rosheta;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rosheta.data.models.remote.User;
import com.example.rosheta.interfaces.EndPoints;
import com.example.rosheta.views.networking.RetrofitCreation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText email, password;
        email = findViewById(R.id.mail);
        password = findViewById(R.id.pass);
        Button btn = findViewById(R.id.login);


        EndPoints Api = RetrofitCreation.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<User> call = Api.login(email.getText().toString(), password.getText().toString());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.e("res", response.body().email);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });


    }
}