package com.example.rosheta.views.pages.parents;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosheta.R;
import com.example.rosheta.interfaces.CallBack;

import java.util.List;
import java.util.Locale;


/**
 * This Activity ids the base activity for the project, it contains
 * all methods i almost use frequently in the project to prevent the repetition of
 * code snippets.
 */
public class BaseActivity extends AppCompatActivity {

    public static String getCompleteAddressString(Context context, double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder();
                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strAdd;
    }

    /**
     * Navigation between two screens
     */
    public void go_screen(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        startActivity(intent);
    }

    /**
     * Handler to make delay for certain time then implement onFinished() which
     * located in CallBack interface.
     */
    public void delay(int duration, CallBack callBack) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callBack.onFinished();
            }
        }, duration);
    }

    /**
     * Change the color of the edit text border if it is focused.
     */
    public void changeBorderOnFocus(int... ids) {
        for (int id : ids) {
            EditText view = findViewById(id);
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean isFocused) {
                    if (isFocused == true) {
                        view.setBackgroundResource(R.drawable.et_bg_1);
                    } else {
                        view.setBackgroundResource(R.drawable.et_bg);

                    }
                }
            });
        }
    }

    /**
     * get Edit text text
     */
    public String getEtText(int id) {
        EditText et = findViewById(id);
        return et.getText().toString();
    }

    public void replaceFragment(Fragment fragment, int frameId) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(frameId, fragment, backStateName);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    public void go_next(View v) {

        if (v instanceof Button)
            v.performClick();
        else if (v instanceof EditText)
            v.requestFocus();


    }


}
