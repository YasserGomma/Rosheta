package com.example.rosheta.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rosheta.R;
import com.example.rosheta.data.source.remote.Examination;
import com.example.rosheta.views.pages.c_home.ExaminationDetails;

import java.util.ArrayList;

public class ExaminationsAdapter extends RecyclerView.Adapter<ExaminationsAdapter.ExaminationItemViewHolder> {
    public static String examinationID_Adapter = "";
    public static Examination examination = new Examination();
    ArrayList<Examination> examinations = new ArrayList<>();
    Context context;

    public ExaminationsAdapter(Context context, ArrayList<Examination> examinations) {
        this.examinations = examinations;
        this.context = context;

    }

    @Override
    public ExaminationItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_examination, null, false);
        ExaminationItemViewHolder examinationItemViewHolder = new ExaminationItemViewHolder(v);
        return examinationItemViewHolder;
    }


    @Override
    public void onBindViewHolder(ExaminationItemViewHolder holder, int position) {

        Examination item = examinations.get(position);
        if (position > 0) {
            holder.tv_examination_doc_name.setText(item.getDoctor_name());
            holder.tv_examination_date.setText(item.getCreated_at() + "");
            holder.tv_examination_price.setText(item.getPrice() + " EGP");
            holder.tv_examination_id.setText(item.getId() + "");
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    examination = item;
                    examinationID_Adapter = (item.getId() + "");
                    Intent i = new Intent(context, ExaminationDetails.class);
                    context.startActivity(i);
                }
            });
        } else {
            holder.cardView.setVisibility(View.GONE);
            holder.tv_examination_cnt.setText("Found " + (examinations.size() - 1) + "\nExaminations");
        }

    }

    @Override
    public int getItemCount() {
        return examinations.size();
    }

    class ExaminationItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_examination_doc_name, tv_examination_id, tv_examination_price, tv_examination_date, tv_examination_cnt;
        RelativeLayout layout;
        CardView cardView;

        public ExaminationItemViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_examination_layout);
            tv_examination_doc_name = itemView.findViewById(R.id.tv_pharmacy_name);
            tv_examination_price = itemView.findViewById(R.id.tv_pharmacy_dis);
            tv_examination_date = itemView.findViewById(R.id.tv_doc_sp);
            tv_examination_id = itemView.findViewById(R.id.tv_doc_name);
            cardView = itemView.findViewById(R.id.card);
            tv_examination_cnt = itemView.findViewById(R.id.tv_examination_cnt);


        }
    }
}
