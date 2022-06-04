package com.example.rosheta.views.pages.b_account;

import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rosheta.R;
import com.example.rosheta.views.components.TwoTexts;
import com.example.rosheta.views.pages.parents.BaseActivity;

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

    }
}