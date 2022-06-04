package com.example.rosheta.views.pages.a_intro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rosheta.R;
import com.example.rosheta.views.components.TwoTexts;
import com.example.rosheta.views.pages.b_account.Login;
import com.example.rosheta.views.pages.parents.BaseActivity;

public class ChooseScreen extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_screen);

        //Go to Sign up screen via on boarding screen
        Button btn=findViewById(R.id.btn_welcome_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_screen(ChooseScreen.this, onBoardingScreen.class);
            }
        });

        //Go to Login Screen
        TwoTexts t = findViewById(R.id.two);
        TextView tv = t.findViewById(R.id.two_texts_clickable_text);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_screen(ChooseScreen.this, Login.class);
            }
        });

    }


}