package com.example.fikfishdriver.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fikfishdriver.R;
import com.example.fikfishdriver.model.history.DataHistory;
import com.example.fikfishdriver.ui.shipping.activity.DetailShippingActivity;

import java.util.List;

public class AdapterHistoryDate extends RecyclerView.Adapter<AdapterHistoryDate.ViewHolder> {

    private Context context;
    private List<DataHistory> listData;

    public AdapterHistoryDate(Context context, List<DataHistory> dataHistory) {
        this.context = context;
        this.listData = dataHistory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  v = LayoutInflater.from(context).inflate(R.layout.container_history,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataHistory dataHistory = listData.get(position);
        holder.tv_nama.setText(dataHistory.getFullName());
        holder.tv_alamat.setText(dataHistory.getAlamat());
        holder.tv_status.setText(dataHistory.getStatus());
//        holder.cardShipping.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent detail = new Intent(context, DetailShippingActivity.class);
//                detail.putExtra("data",dataHistory);
//                context.startActivity(detail);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nama,tv_alamat,tv_status;
        CardView cardShipping;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_alamat = itemView.findViewById(R.id.tv_alamat);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_status = itemView.findViewById(R.id.tv_status);
            cardShipping = itemView.findViewById(R.id.cardShipping);
        }
    }
}
