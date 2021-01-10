package com.example.fikfishdriver.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fikfishdriver.R;
import com.example.fikfishdriver.model.history.DataHistory;
import com.example.fikfishdriver.network.repository.history.DetailHistoryActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolder> {

    private Context context;
    private List<DataHistory> listData;

    public AdapterHistory(Context context, List<DataHistory> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_riwayat,parent,false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataHistory dataHistory = listData.get(position);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format2 = new SimpleDateFormat("EEEE");
        Locale id = new Locale("in","ID");
        String date = dataHistory.getDateShipping();
        String[] parts = date.split("-");
        holder.tv_tanggal.setText(parts[2]);
        try {
            String hari = format2.format(Objects.requireNonNull(format1.parse(date)));
            System.out.println(hari);
            holder.tv_hari.setText(hari);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.cardHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(context, DetailHistoryActivity.class);
                detail.putExtra("data",dataHistory);
                context.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardHistory;
        TextView tv_hari,tv_tanggal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardHistory = itemView.findViewById(R.id.cardHistory);
            tv_hari = itemView.findViewById(R.id.tv_hari);
            tv_tanggal = itemView.findViewById(R.id.tv_tanggal);
        }
    }
}
