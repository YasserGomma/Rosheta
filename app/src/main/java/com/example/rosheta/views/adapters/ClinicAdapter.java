package com.example.rosheta.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rosheta.data.models.remote.Pharmacy;
import com.example.rosheta.views.pages.c_home.ClinicDetailes;
import com.example.rosheta.R;
import com.example.rosheta.data.models.remote.Clinc;
import com.example.rosheta.views.pages.c_home.Home;
import com.example.rosheta.views.pages.parents.BaseActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClinicAdapter extends RecyclerView.Adapter<ClinicAdapter.ClinicItemViewHolder> {
    public static String clinicID_Adapter = "";
    public static Clinc clinc = new Clinc();
    ArrayList<Clinc> clincs = new ArrayList<>();
    Context context;

    public ClinicAdapter(Context context, ArrayList<Clinc> clincs) {
        this.clincs = clincs;
        this.context = context;
        Collections.sort(clincs,new Comparator<Clinc>(){
            @Override
            public int compare(final Clinc lhs,Clinc rhs) {
                //TODO return 1 if rhs should be before lhs
                //     return -1 if lhs should be before rhs
                //     return 0 otherwise (meaning the order stays the same)
                LatLng L = new LatLng(Double.parseDouble(lhs.getLat()),Double.parseDouble(lhs.getLng()));
                LatLng R = new LatLng(Double.parseDouble(rhs.getLat()),Double.parseDouble(rhs.getLng()));
                LatLng dest=new LatLng(Home.lat,Home.longi);

                double distancel=BaseActivity.calcDistance(dest,L);
                double distancer=BaseActivity.calcDistance(dest,R);

                if (distancel>distancer)
                    return 1;
                return -1;
            }
        });

    }

    @Override
    public ClinicItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clinic, null, false);
        ClinicItemViewHolder clinicItemViewHolder = new ClinicItemViewHolder(v);
        return clinicItemViewHolder;
    }


    @Override
    public void onBindViewHolder(ClinicItemViewHolder holder, int position) {
        Clinc item = clincs.get(position);
        holder.tv_clinic_name.setText(item.getLName());
        holder.tv_doc_name.setText(item.getName());
        holder.tv_doc_sp.setText(item.getSName());
        LatLng cur = new LatLng(Double.parseDouble(item.getLat()),Double.parseDouble(item.getLng()));
        LatLng a,b;
        a=new LatLng(Double.parseDouble(item.getLat()),Double.parseDouble(item.getLng()));
        b=new LatLng(Home.lat,Home.longi);
        double distance=BaseActivity.calcDistance(a,b);
        holder.tv_clinic_dis.setText(((int)distance>=1000?(int)distance/1000+","+(int)distance%1000+" Km":(int)distance+"M"));

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clinc = item;
                LatLng cur = new LatLng(Double.parseDouble(item.getLat()), Double.parseDouble(item.getLng()));
                Home.googleMap.addMarker(new MarkerOptions()
                        .position(cur)
                        .title(item.getName()));
                GoogleMap googleMap;
                Home.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cur, 17));
                clinicID_Adapter = (item.getId() + "");
                //Intent i = new Intent(context, ExaminationDetails.class);
                //  context.startActivity(i);
            }
        });

        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clinc = item;
                Intent i = new Intent(context, ClinicDetailes.class);
                context.startActivity(i);
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return clincs.size();
    }

    class ClinicItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_clinic_name, tv_clinic_dis, tv_doc_name, tv_doc_sp;
        RelativeLayout layout;

        public ClinicItemViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_clinic_layout);
            tv_clinic_name = itemView.findViewById(R.id.tv_pharmacy_name);
            tv_doc_name = itemView.findViewById(R.id.tv_doc_name);
            tv_doc_sp = itemView.findViewById(R.id.tv_doc_sp);
            tv_clinic_dis = itemView.findViewById(R.id.tv_pharmacy_dis);


        }
    }
}
