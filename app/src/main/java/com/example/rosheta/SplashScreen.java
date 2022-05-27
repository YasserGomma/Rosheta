package com.example.rosheta;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    TextView txt;
    ImageView top, bottom, logo;
    CharSequence charSequence;
    int index;
    long delay = 100;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //  Assign variables
        txt = findViewById(R.id.splash_tv_txt);
        top = findViewById(R.id.splash_iv_top);
        bottom = findViewById(R.id.splash_iv_bottom);
        logo = findViewById(R.id.splash_iv_logo);


        //Initialize top animation
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.top_wave);

        //Start top animation
       // top.setAnimation(animation1);

        //Initialize object animator for logo
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                logo,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        );

        //Set duration
        objectAnimator.setDuration(4000);

        //Set repeat count
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);

        //Set repeat mode
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);

        //Start animator
       objectAnimator.start();


        //Set animate text
        animateText("All Health-Care In One Place!");


        //Initialize bottom animation
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.bottom_wave);

        //Start top animation
        //bottom.setAnimation(animation2);


        //Initialize handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class).
                        setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        }, 4000);


    }

    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            txt.setText(charSequence.subSequence(0, index++));
            if (index <= charSequence.length()) {
                handler.postDelayed(runnable, delay);
            }
        }
    };

    public void animateText(CharSequence c) {
        charSequence = c;
        index = 0;
        txt.setText("");
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, delay);
    }
}