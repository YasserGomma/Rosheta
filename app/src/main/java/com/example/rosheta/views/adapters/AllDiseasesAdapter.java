package com.example.rosheta.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rosheta.R;
import com.example.rosheta.data.models.remote.AllDiseases;

import java.util.ArrayList;

public class AllDiseasesAdapter extends RecyclerView.Adapter<AllDiseasesAdapter.DiseasesItemViewHolder> {
    public static String medicineID_Adapter = "";
    ArrayList<AllDiseases> diseases = new ArrayList<>();
    Context context;

    public AllDiseasesAdapter(Context context, ArrayList<AllDiseases> diseases) {
        this.diseases = diseases;
        this.context = context;

    }

    @Override
    public DiseasesItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report, null, false);
        DiseasesItemViewHolder diseasesItemViewHolder = new DiseasesItemViewHolder(v);
        return diseasesItemViewHolder;
    }


    @Override
    public void onBindViewHolder(DiseasesItemViewHolder holder, int position) {

        AllDiseases item = diseases.get(position);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.item_report_name.setText(item.getName());
        holder.item_report_description.setText(item.getDescription());




    }

    @Override
    public int getItemCount() {
        return diseases.size();
    }

    class DiseasesItemViewHolder extends RecyclerView.ViewHolder {
        TextView item_report_name, item_report_description, item_medicine_price;
        RelativeLayout layout;

        public DiseasesItemViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_report_layout);
            item_report_name = itemView.findViewById(R.id.item_report_name);
            item_report_description = itemView.findViewById(R.id.item_report_description);


        }
    }
}
