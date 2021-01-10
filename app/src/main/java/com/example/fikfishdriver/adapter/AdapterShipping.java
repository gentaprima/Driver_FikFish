package com.example.fikfishdriver.adapter;

import android.annotation.SuppressLint;
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
import com.example.fikfishdriver.model.shippinng.DataShipping;
import com.example.fikfishdriver.ui.shipping.activity.DetailShippingActivity;

import java.util.List;

public class AdapterShipping extends RecyclerView.Adapter<AdapterShipping.ViewHolder> {

    private Context context;
    private List<DataShipping> listData;

    public AdapterShipping(Context context, List<DataShipping> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.container_shipping,parent,false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final DataShipping dataShipping = listData.get(position);
        holder.tv_nama.setText(dataShipping.getFullName());
        holder.tv_alamat.setText(dataShipping.getAlamat());
        String jarak = String.valueOf(dataShipping.getJarak());
        holder.tv_jarak.setText(jarak.substring(0,3) + "KM");
        holder.cardShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(context, DetailShippingActivity.class);
                detail.putExtra("data",dataShipping);
                context.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nama,tv_alamat,tv_jarak;
        CardView cardShipping;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_alamat = itemView.findViewById(R.id.tv_alamat);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_jarak = itemView.findViewById(R.id.tv_jarak);
            cardShipping = itemView.findViewById(R.id.cardShipping);
        }
    }
}
