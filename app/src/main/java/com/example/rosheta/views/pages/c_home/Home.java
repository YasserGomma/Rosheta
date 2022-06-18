package com.example.rosheta.views.pages.c_home;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.rosheta.R;
import com.example.rosheta.views.pages.a_intro.ChooseScreen;
import com.example.rosheta.views.pages.b_account.Login;
import com.example.rosheta.views.pages.parents.BaseActivity;
import com.google.android.material.navigation.NavigationView;

public class Home extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        NavigationView navigationView = findViewById(R.id.navigationView);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.tv_header_menu_name);
        TextView navUserEmail = headerView.findViewById(R.id.tv_header_menu_email);

        ImageView menu_iv = headerView.findViewById(R.id.iv_header_menu_pic);

        Button logout = navigationView.findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().clear().apply();
                go_screen(Home.this, ChooseScreen.class);
            }
        });

        navUsername.setText(Login.user.getName());
        navUserEmail.setText(Login.user.getEmail());
        DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
       findViewById(R.id.btn_home_header_menu).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               drawerLayout.openDrawer(GravityCompat.START);
           }
       });

        navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        initNavigationMenu();

    }
    private void initNavigationMenu() {
        NavigationView navigationView = findViewById(R.id.navigationView);
        final DrawerLayout drawer = findViewById(R.id.drawerlayout);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                menuSelection(item.getItemId());
                drawer.closeDrawers();
                return true;
            }
        });

    }

    public void menuSelection(int id) {
        switch (id) {
            case R.id.examinations:
                go_screen(Home.this, Examinations.class);
                break;
            default:

        }
    }

}