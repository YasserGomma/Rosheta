package com.example.rosheta.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rosheta.views.pages.c_home.ClinicDetailes;
import com.example.rosheta.R;
import com.example.rosheta.data.source.remote.Clinc;
import com.example.rosheta.views.pages.c_home.Home;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class ClinicAdapter extends RecyclerView.Adapter<ClinicAdapter.ClinicItemViewHolder> {
    public static String clinicID_Adapter = "";
    public static Clinc clinc = new Clinc();
    ArrayList<Clinc> clincs = new ArrayList<>();
    Context context;

    public ClinicAdapter(Context context, ArrayList<Clinc> clincs) {
        this.clincs = clincs;
        this.context = context;

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
        holder.tv_clinic_dis.setText(item.getDistance() + "");

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
