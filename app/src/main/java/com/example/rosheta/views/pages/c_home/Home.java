package com.example.rosheta.views.pages.c_home;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rosheta.R;
import com.example.rosheta.data.source.remote.Clinc;
import com.example.rosheta.data.source.remote.Pharmacy;
import com.example.rosheta.interfaces.CallBack;
import com.example.rosheta.interfaces.EndPoints;
import com.example.rosheta.views.adapters.ClinicAdapter;
import com.example.rosheta.views.adapters.PharmacyAdapter;
import com.example.rosheta.views.networking.RetrofitCreation;
import com.example.rosheta.views.pages.a_intro.ChooseScreen;
import com.example.rosheta.views.pages.b_account.Login;
import com.example.rosheta.views.pages.parents.BaseActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;


public class Home extends BaseActivity implements OnMapReadyCallback {
    TextView showLocation;
    LocationManager locationManager;
    String latitude, longitude;
    double lat ;
    double longi;
    SupportMapFragment map;
    int count = 0;

    LatLng source;
    LatLng destination;
    public static GoogleMap googleMap;
    private static final int REQUEST_LOCATION = 1;
    Button btnGetLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ActivityCompat.requestPermissions( this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        showLocation = findViewById(R.id.lbl_src);

        btnGetLocation = findViewById(R.id.btnGetLocation);
        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    OnGPS();
                } else {
                    getLocation();
                }
            }
        });


        BaseActivity.delay(500, new CallBack() {
            @Override
            public void onFinished() {
                btnGetLocation.performClick();
            }
        });


        //Clinics
        RecyclerView recyclerView = findViewById(R.id.rv_home_clinics);
        StaggeredGridLayoutManager layoutManager
                = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        EndPoints Api = RetrofitCreation.getInstance();
        ArrayList<Clinc> clincs = new ArrayList<>();
        Call<ArrayList<Clinc>> call = Api.getClinic("clinic", "");
        call.enqueue(new Callback<ArrayList<Clinc>>() {
            @Override
            public void onResponse(Call<ArrayList<Clinc>> call, retrofit2.Response<ArrayList<Clinc>> response) {
                for (int i = 0; i < response.body().size(); i++)
                    clincs.add(response.body().get(i));
                ClinicAdapter clinicAdapter = new ClinicAdapter(Home.this, clincs);
                recyclerView.setAdapter(clinicAdapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onFailure(Call<ArrayList<Clinc>> call, Throwable t) {

            }
        });

        //Pharmacies
        RecyclerView recyclerView2 = findViewById(R.id.rv_home_pharmacies);
        StaggeredGridLayoutManager layoutManager2
                = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        recyclerView2.setLayoutManager(layoutManager2);
        ArrayList<Pharmacy> pharmacies = new ArrayList<>();
        Call<ArrayList<Pharmacy>> call2 = Api.getPharmacy("pharmacy", "");
        call2.enqueue(new Callback<ArrayList<Pharmacy>>() {
            @Override
            public void onResponse(Call<ArrayList<Pharmacy>> call, retrofit2.Response<ArrayList<Pharmacy>> response) {
                for (int i = 0; i < response.body().size(); i++)
                    pharmacies.add(response.body().get(i));
                PharmacyAdapter pharmacyAdapter = new PharmacyAdapter(Home.this, pharmacies);
                recyclerView2.setAdapter(pharmacyAdapter);
                recyclerView2.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onFailure(Call<ArrayList<Pharmacy>> call, Throwable t) {

            }
        });


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

        // getCurrentLocation();


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


        msg("onCreate");

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 300);
        map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);


    }

    void msg(String m) {
        Log.e("-", m);
    }


    public void menuSelection(int id) {
        switch (id) {
            case R.id.examinations:
                go_screen(Home.this, Examinations.class);
                break;
            case R.id.examinations_request:
                go_screen(Home.this, ExaminationRequests.class);
                break;
            case R.id.medicines:
                go_screen(Home.this, Medicines.class);
                break;
            default:
                break;

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        Log.e("res", "map ready");
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {



            }
        });
        googleMap.setMyLocationEnabled(true);
      //  googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude)),17));
        //getLocation(new LatLng(29.9846688,31.230339));

    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                Home.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                Home.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                 lat = locationGPS.getLatitude();
                 longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                showLocation.setText(BaseActivity.getCompleteAddressString(Home.this,lat,longi));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude)),17));



            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

       void drawRoute(LatLng source, LatLng destination) {
        StringRequest request = new StringRequest(0, "https://maps.googleapis.com/maps/api/directions/json?origin=" + source.latitude + "," + source.longitude +
                "&destination=" + destination.latitude + "," + destination.longitude + "&key=AIzaSyCJv8cTkf-d57zjvWRmZZOD22rnSP8NYK0&language=ar",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject js = new JSONObject(response);
                            JSONArray routes = js.getJSONArray("routes");
                            JSONObject route = routes.getJSONObject(0);
                            JSONObject leg = route.getJSONArray("legs").getJSONObject(0);
                            String distance = leg.getJSONObject("distance").getString("text");
                            String duration = leg.getJSONObject("duration").getString("text");

                            //   TextView lbl_distance = findViewById(R.id.lbl_distance);
                            //TextView lbl_duration = findViewById(R.id.lbl_duration);

                            String points = route.getJSONObject("overview_polyline").getString("points");

                            googleMap.addPolyline(new PolylineOptions().addAll(decodePoly(points)).color(Color.BLACK).width(8).geodesic(true));

                            //  lbl_distance.setText(distance);
                            //   lbl_duration.setText(duration);

                        } catch (JSONException e) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(Home.this);
        queue.add(request);
    }

    void gotoPlace(String place_id) {
        StringRequest request = new StringRequest(0, "https://maps.googleapis.com/maps/api/place/details/json?place_id=" + place_id + "&key=KK",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject js = new JSONObject(response);
                            JSONObject location = js.getJSONObject("result").
                                    getJSONObject("geometry").getJSONObject("location");
                            Double lat = location.getDouble("lat");
                            Double lng = location.getDouble("lng");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 17));

                        } catch (JSONException e) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(Home.this);
        queue.add(request);
    }


    private ArrayList<LatLng> decodePoly(String encoded) {
        ArrayList<LatLng> poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }


}