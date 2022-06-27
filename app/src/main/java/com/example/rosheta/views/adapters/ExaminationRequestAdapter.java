package com.example.rosheta.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rosheta.R;
import com.example.rosheta.data.source.remote.ExaminationRequest;
import com.example.rosheta.views.pages.parents.BaseActivity;

import java.util.ArrayList;

public class ExaminationRequestAdapter extends RecyclerView.Adapter<ExaminationRequestAdapter.ExaminationRequestItemViewHolder> {
    public static String medicineID_Adapter = "";
    ArrayList<ExaminationRequest> examinationRequests = new ArrayList<>();
    Context context;

    public ExaminationRequestAdapter(Context context, ArrayList<ExaminationRequest> examinationRequests) {
        this.examinationRequests = examinationRequests;
        this.context = context;

    }

    @Override
    public ExaminationRequestItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_examination_request, null, false);
        ExaminationRequestItemViewHolder examinationRequestItemViewHolder = new ExaminationRequestItemViewHolder(v);
        return examinationRequestItemViewHolder;
    }


    @Override
    public void onBindViewHolder(ExaminationRequestItemViewHolder holder, int position) {

        ExaminationRequest item = examinationRequests.get(position);
        if (position > 0) {
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            holder.item_examination_request_name.setText(item.getName());
            holder.item_examination_request_address.setText(BaseActivity.getCompleteAddressString(context, Double.parseDouble(item.getLat()), Double.parseDouble(item.getLng())));
            holder.item_examination_request_status.setText(item.getStatus());
            holder.item_examination_request_date.setText(item.getCreated_at()+" ");
            if (!item.getStatus().equals("accepted")) {
                holder.item_examination_request_status.setTextColor(Color.RED);
            }
        } else {
            holder.cardView.setVisibility(View.GONE);
            holder.item_examination_request_cnt.setText("Found " + (examinationRequests.size() - 1) + "\nExamination Request(s)");

        }


    }

    @Override
    public int getItemCount() {
        return examinationRequests.size();
    }

    class ExaminationRequestItemViewHolder extends RecyclerView.ViewHolder {
        TextView item_examination_request_name, item_examination_request_address, item_examination_request_status, item_examination_request_cnt,item_examination_request_date;
        RelativeLayout layout;
        CardView cardView;


        public ExaminationRequestItemViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_examination_request_layout);
            item_examination_request_name = itemView.findViewById(R.id.item_examination_request_name);
            item_examination_request_address = itemView.findViewById(R.id.item_examination_request_address);
            item_examination_request_status = itemView.findViewById(R.id.item_examination_request_status);
            item_examination_request_cnt = itemView.findViewById(R.id.item_examination_request_cnt);
            item_examination_request_date=itemView.findViewById(R.id.item_examination_request_date);
            cardView = itemView.findViewById(R.id.card);


        }
    }
}
