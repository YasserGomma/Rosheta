package com.example.rosheta.views.pages.b_account;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rosheta.R;
import com.example.rosheta.data.source.remote.User;
import com.example.rosheta.data.source.remote.UserR;
import com.example.rosheta.interfaces.EndPoints;
import com.example.rosheta.views.components.InputField;
import com.example.rosheta.views.components.TwoTexts;
import com.example.rosheta.views.networking.RetrofitCreation;
import com.example.rosheta.views.pages.parents.BaseActivity;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Go back from hearer button
        findViewById(R.id.btn_back_header_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SignUp.super.onBackPressed();
            }
        });

        //Go to login screen
        TwoTexts t = findViewById(R.id.signup_login);
        TextView tv = t.findViewById(R.id.two_texts_clickable_text);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_screen(SignUp.this, Login.class);
            }
        });


        //register
        //inflate input fields
        InputField signup_fullname, signup_email, signup_password, signup_password2, signup_id, signup_birth_date;
        signup_fullname = findViewById(R.id.signup_fullname);
        signup_email = findViewById(R.id.signup_email);
        signup_password = findViewById(R.id.signup_password);
        signup_password2 = findViewById(R.id.signup_password2);
        signup_id = findViewById(R.id.signup_id);
        signup_birth_date = findViewById(R.id.signup_birth_date);

        //inflate editTexts from input fields
        EditText et_signup_fullname,
                et_signup_email,
                et_signup_password,
                et_signup_password2,
                et_signup_id,
                et_signup_birth_date,
                et_signup_phone;
        et_signup_fullname = signup_fullname.findViewById(R.id.input_field_edit_text);
        et_signup_email = signup_email.findViewById(R.id.input_field_edit_text);
        et_signup_password = signup_password.findViewById(R.id.input_field_edit_text);
        et_signup_password2 = signup_password2.findViewById(R.id.input_field_edit_text);
        et_signup_id = signup_id.findViewById(R.id.input_field_edit_text);
        et_signup_birth_date = signup_birth_date.findViewById(R.id.input_field_edit_text);
        et_signup_phone = findViewById(R.id.et_signup_phone);

        //assign data to strings from editTexts


        //set signup button listener
        Button btn_signup_signup = findViewById(R.id.btn_signup_signup);
        btn_signup_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String full_name, email, password, password2, id, birth_date, phone;
                full_name = et_signup_fullname.getText().toString();
                email = et_signup_email.getText().toString();
                password = et_signup_password.getText().toString();
                password2 = et_signup_password2.getText().toString();
                id = et_signup_id.getText().toString();
                birth_date = et_signup_birth_date.getText().toString();
                phone = et_signup_phone.getText().toString();

                EndPoints Api = RetrofitCreation.getInstance();
                UserR userR=new UserR(full_name,id,phone,email,password2,password);
                Log.e("user",userR.toString());

                Call<User> call = Api.register(userR);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.e("register success",response.body().email);
                        Toast.makeText(getApplicationContext(),
                                response.body().toString(),
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("register fail", t.getMessage().toString());
                        Toast.makeText(getApplicationContext(),
                                t.getMessage()  ,
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });


    }
}