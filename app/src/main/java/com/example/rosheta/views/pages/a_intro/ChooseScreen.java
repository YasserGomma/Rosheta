package com.example.rosheta.views.pages.a_intro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rosheta.R;
import com.example.rosheta.views.pages.parents.BaseActivity;

public class ChooseScreen extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_screen);

        Button btn=findViewById(R.id.btn_welcome_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_screen(ChooseScreen.this, onBoardingScreen.class);
            }
        });
    }


}