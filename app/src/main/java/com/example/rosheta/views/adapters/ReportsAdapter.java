package com.example.rosheta.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rosheta.R;
import com.example.rosheta.data.models.remote.Report;

import java.util.ArrayList;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.ReportsItemViewHolder> {
    public static String medicineID_Adapter = "";
    ArrayList<Report> reports = new ArrayList<>();
    Context context;

    public ReportsAdapter(Context context, ArrayList<Report> reports) {
        this.reports = reports;
        this.context = context;

    }

    @Override
    public ReportsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report, null, false);
        ReportsItemViewHolder reportsItemViewHolder = new ReportsItemViewHolder(v);
        return reportsItemViewHolder;
    }


    @Override
    public void onBindViewHolder(ReportsItemViewHolder holder, int position) {

        Report item = reports.get(position);
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
        return reports.size();
    }

    class ReportsItemViewHolder extends RecyclerView.ViewHolder {
        TextView item_report_name, item_report_description, item_medicine_price;
        RelativeLayout layout;

        public ReportsItemViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_report_layout);
            item_report_name = itemView.findViewById(R.id.item_report_name);
            item_report_description = itemView.findViewById(R.id.item_report_description);


        }
    }
}
