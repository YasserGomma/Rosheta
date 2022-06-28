package com.example.rosheta.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rosheta.R;
import com.example.rosheta.data.models.remote.Pharmacy;
import com.example.rosheta.interfaces.CallBack;
import com.example.rosheta.views.pages.c_home.Home;
import com.example.rosheta.views.pages.parents.BaseActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.PharmacyItemViewHolder> {
    public static String pharmacyID_Adapter = "";
    public static Pharmacy pharmacy = new Pharmacy();
    ArrayList<Pharmacy> pharmacies = new ArrayList<>();
    Context context;

    public PharmacyAdapter(Context context, ArrayList<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
        this.context = context;

    }

    @Override
    public PharmacyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pharmacy, null, false);
        PharmacyItemViewHolder pharmacyItemViewHolder = new PharmacyItemViewHolder(v);
        return pharmacyItemViewHolder;
    }


    @Override
    public void onBindViewHolder(PharmacyItemViewHolder holder, int position) {
        Pharmacy item = pharmacies.get(position);
        LatLng cur = new LatLng(Double.parseDouble(item.getLat()),Double.parseDouble(item.getLng()));

        LatLng a,b;
        a=new LatLng(Double.parseDouble(item.getLat()),Double.parseDouble(item.getLng()));
        b=new LatLng(Home.lat,Home.longi);

        double distance=BaseActivity.calcDistance(a,b);
        holder.tv_pharmacy_name.setText(item.getName());
        holder.tv_pharmacy_dis.setText(((int)distance>=1000?(int)distance/1000+","+(int)distance%1000+" Km":(int)distance+"M"));

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pharmacy = item;
                pharmacyID_Adapter = (item.getId()+"");

                Home.googleMap.addMarker(new MarkerOptions()
                        .position(cur)
                        .title(item.getName()));
                GoogleMap googleMap;
                Home.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cur,17));

                if(!context.getClass().equals(Home.class))
                {

                    BaseActivity.delay(1000, new CallBack() {
                        @Override
                        public void onFinished() {

                            Home.googleMap.addMarker(new MarkerOptions()
                                    .position(cur)
                                    .title(item.getName())
                                    .snippet("About "+((int)distance>=1000?(int)distance/1000+","+(int)distance%1000+" Km":(int)distance+"M")+"\n Click long press to GO!")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pharmacy)));
                            GoogleMap googleMap;
                            Home.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cur,17));
                        }
                    });
                    Intent i = new Intent(context, Home.class);
                    context.startActivity(i);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return pharmacies.size();
    }

    class PharmacyItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_pharmacy_name, tv_pharmacy_dis;
        RelativeLayout layout;

        public PharmacyItemViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_pharmacy_layout);
            tv_pharmacy_name = itemView.findViewById(R.id.tv_pharmacy_name);
            tv_pharmacy_dis = itemView.findViewById(R.id.tv_pharmacy_dis);


        }
    }
}
