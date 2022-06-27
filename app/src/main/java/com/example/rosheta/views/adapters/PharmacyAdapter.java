package com.example.rosheta.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rosheta.R;
import com.example.rosheta.data.source.remote.Pharmacy;
import com.example.rosheta.views.pages.c_home.Home;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
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
        holder.tv_pharmacy_name.setText(item.getName());
        holder.tv_pharmacy_dis.setText(item.getDistance()+"");

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pharmacy = item;
                pharmacyID_Adapter = (item.getId()+"");

                LatLng cur = new LatLng(Double.parseDouble(item.getLat()),Double.parseDouble(item.getLng()));
                Home.googleMap.addMarker(new MarkerOptions()
                        .position(cur)
                        .title(item.getName()));
                GoogleMap googleMap;
                Home.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cur,17));
                //Intent i = new Intent(context, ExaminationDetails.class);
                //  context.startActivity(i);
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
