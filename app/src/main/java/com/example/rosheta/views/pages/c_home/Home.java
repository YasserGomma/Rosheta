package com.example.rosheta.views.pages.c_home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rosheta.R;
import com.example.rosheta.views.pages.a_intro.ChooseScreen;
import com.example.rosheta.views.pages.b_account.Login;
import com.example.rosheta.views.pages.parents.BaseActivity;
import com.example.rosheta.views.pages.parents.GPSTracker;
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
import java.util.List;



public class Home extends BaseActivity implements OnMapReadyCallback {
    public static Double latitude, longitude;
    SupportMapFragment map;
    int count = 0;

    LatLng source;
    LatLng destination;
    GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);
        GPSTracker mGPS = new GPSTracker(this);
        TextView text = (TextView) findViewById(R.id.add);
        if (mGPS.canGetLocation) {
            mGPS.getLocation();
            latitude=mGPS.getLatitude();
            longitude=mGPS.getLongitude();
            text.setText(BaseActivity.getCompleteAddressString(this, mGPS.getLatitude(), mGPS.getLongitude()));
        } else {
            text.setText("Unabletofind");
        }

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

//        navUsername.setText(Login.user.getName());
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
            case R.id.examinations_request:
                go_screen(Home.this, ExaminationRequests.class);
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
                //googleMap.clear();
                if(count < 2){
                    googleMap.addMarker(new MarkerOptions().
                            position(latLng).
                            title("nada's Hospital").icon(BitmapDescriptorFactory.fromResource(R.drawable.back)));
                }
                if(count == 0){
                    source = latLng;
                    getLocation(latLng,0);
                }
                if(count == 1){
                    destination = latLng;
                    getLocation(latLng,1);
                    drawRoute(source,destination);
                }
                count++;


            }
        });
        googleMap.setMyLocationEnabled(true);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(29.9846688,31.230339),17));
        //getLocation(new LatLng(29.9846688,31.230339));

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    void getLocation(LatLng latLng, int count){
        StringRequest request = new StringRequest(0, "https://maps.googleapis.com/maps/api/geocode/json?latlng="+latLng.latitude+","+latLng.longitude+"&key=K&language=ar",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject js = new JSONObject(response);
                            JSONArray results = js.getJSONArray("results");
                            JSONObject place = results.getJSONObject(0);
                            String address = place.getString("formatted_address");

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

    void drawRoute(LatLng source,LatLng destination){
        StringRequest request = new StringRequest(0, "https://maps.googleapis.com/maps/api/directions/json?origin="+source.latitude+","+source.longitude+
                "&destination="+destination.latitude+","+destination.longitude+"&key=KK&language=ar",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject js = new JSONObject(response);
                            JSONArray routes = js.getJSONArray("routes");
                            JSONObject route =  routes.getJSONObject(0);
                            JSONObject leg = route.getJSONArray("legs").getJSONObject(0);
                            String distance = leg.getJSONObject("distance").getString("text");
                            String duration = leg.getJSONObject("duration").getString("text");



                            String points = route.getJSONObject("overview_polyline").getString("points");

                            googleMap.addPolyline(new PolylineOptions().addAll(decodePoly(points)).color(Color.BLACK).width(8).geodesic(true));


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

    void gotoPlace(String place_id){
        StringRequest request = new StringRequest(0, "https://maps.googleapis.com/maps/api/place/details/json?place_id="+place_id+"&key=KK",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject js = new JSONObject(response);
                            JSONObject location = js.getJSONObject("result").
                                    getJSONObject("geometry").getJSONObject("location");
                            Double lat = location.getDouble("lat");
                            Double lng = location.getDouble("lng");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lng),17));

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