package com.example.rosheta.views.pages.b_account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rosheta.R;
import com.example.rosheta.data.models.remote.User;
import com.example.rosheta.views.components.InputField;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        User user=Login.user;



        TextView name = findViewById(R.id.tv_profile_name);
        name.setText(Login.user.getName());

        InputField if_profile_full_name = findViewById(R.id.if_profile_full_name);
        EditText et_profile_full_name = if_profile_full_name.findViewById(R.id.input_field_edit_text);
        et_profile_full_name.setText(Login.user.getName());

        InputField if_profile_email = findViewById(R.id.if_profile_mail);
        EditText et_profile_email = if_profile_email.findViewById(R.id.input_field_edit_text);
        et_profile_email.setText(Login.user.getEmail());

        InputField if_profile_phone = findViewById(R.id.if_profile_phone);
        EditText et_profile_phone = if_profile_phone.findViewById(R.id.input_field_edit_text);
        et_profile_phone.setText(Login.user.getNational_id());

        findViewById(R.id.btn_back_header_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Profile.super.onBackPressed();
            }
        });
    }
}