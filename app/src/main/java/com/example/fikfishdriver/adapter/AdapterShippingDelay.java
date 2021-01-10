package com.example.fikfishdriver.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fikfishdriver.R;
import com.example.fikfishdriver.model.shippinng.DataShipping;
import com.example.fikfishdriver.ui.shipping.activity.DetailDelayShippingActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdapterShippingDelay extends RecyclerView.Adapter<AdapterShippingDelay.ViewHolder> {

    private Context context;
    private List<DataShipping> listData;

    public AdapterShippingDelay(Context context, List<DataShipping> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.container_delay,parent,false);
        return  new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataShipping dataShipping = listData.get(position);
        holder.tv_nama.setText(dataShipping.getFullName());
        holder.tv_alamat.setText(dataShipping.getAlamat());
        String jarak = String.valueOf(dataShipping.getJarak());
        holder.tv_jarak.setText(jarak.substring(0,3) + "KM");
        holder.cardShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(context, DetailDelayShippingActivity.class);
                detail.putExtra("data",dataShipping);
                context.startActivity(detail);
            }
        });

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = df.format(c);
        String dateShipping = dataShipping.getDateShipping();

        if(dataShipping.getStatus().equals("Ditunda")){
            holder.colorImage.setImageResource(R.color.colorOrange);
        }else if(formattedDate.equals(dateShipping)){
            holder.colorImage.setImageResource(R.color.colorGreen);
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nama,tv_alamat,tv_jarak;
        CardView cardShipping;
        ImageView colorImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_alamat = itemView.findViewById(R.id.tv_alamat);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_jarak = itemView.findViewById(R.id.tv_jarak);
            cardShipping = itemView.findViewById(R.id.cardShipping);
            colorImage = itemView.findViewById(R.id.colorImage);
        }
    }
}
