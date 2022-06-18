package com.example.rosheta.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rosheta.R;
import com.example.rosheta.data.source.remote.Medicines;

import java.util.ArrayList;

public class MedicinesAdapter extends RecyclerView.Adapter<MedicinesAdapter.MedicinesItemViewHolder> {
    public static String medicineID_Adapter = "";
    ArrayList<Medicines> medicines = new ArrayList<>();
    Context context;

    public MedicinesAdapter(Context context, ArrayList<Medicines> medicines) {
        this.medicines = medicines;
        this.context = context;

    }

    @Override
    public MedicinesItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicine, null, false);
        MedicinesItemViewHolder medicinesItemViewHolder = new MedicinesItemViewHolder(v);
        return medicinesItemViewHolder;
    }


    @Override
    public void onBindViewHolder(MedicinesItemViewHolder holder, int position) {

        Medicines item =medicines.get(position);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.item_medicine_name.setText(item.getName());
        holder.item_medicine_description.setText(item.getDescription());
        holder.item_medicine_price.setText("Price : "+item.getPrice()+"");


    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }

    class MedicinesItemViewHolder extends RecyclerView.ViewHolder {
        TextView item_medicine_name, item_medicine_description, item_medicine_price;
        RelativeLayout layout;

        public MedicinesItemViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_medicine_layout);
            item_medicine_name = itemView.findViewById(R.id.item_medicine_name);
            item_medicine_description = itemView.findViewById(R.id.item_medicine_description);
            item_medicine_price = itemView.findViewById(R.id.item_medicine_price);


        }
    }
}
