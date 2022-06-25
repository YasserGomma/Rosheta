package com.example.rosheta.views.pages.a_intro;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rosheta.R;
import com.example.rosheta.data.source.remote.User;
import com.example.rosheta.interfaces.EndPoints;
import com.example.rosheta.views.components.TwoTexts;
import com.example.rosheta.views.networking.RetrofitCreation;
import com.example.rosheta.views.pages.b_account.Login;
import com.example.rosheta.views.pages.c_home.Home;
import com.example.rosheta.views.pages.parents.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseScreen extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_screen);

        //Go to Sign up screen via on boarding screen
        Button btn = findViewById(R.id.btn_welcome_start);
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

        TextView skip;
        skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChooseScreen.this);
                String mail = preferences.getString("mail", "");
                String password = preferences.getString("pass", "");

                if (!mail.equalsIgnoreCase("") && !password.equalsIgnoreCase("")) {
                    EndPoints Api = RetrofitCreation.getInstance();
                    Call<User> call = Api.login(mail, password);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Login.user = response.body();
                            go_screen(ChooseScreen.this, Home.class);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please login first",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}