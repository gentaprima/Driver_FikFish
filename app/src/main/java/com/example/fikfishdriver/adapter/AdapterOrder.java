package com.example.fikfishdriver.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fikfishdriver.R;
import com.example.fikfishdriver.model.order_transaction.DataTransaction;
import com.example.fikfishdriver.network.Constanta;

import java.util.List;

public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.ViewHolder> {

    private Context context;
    private List<DataTransaction> listData;

    public AdapterOrder(Context context, List<DataTransaction> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.container_order,parent,false);
        return  new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataTransaction dataTransaction = listData.get(position);
        holder.tv_nama.setText(dataTransaction.getNamaIkan());
        holder.tv_qty.setText(dataTransaction.getQuantity() + "x");
        int total = Integer.parseInt(dataTransaction.getHarga()) * Integer.parseInt(dataTransaction.getQuantity());
        holder.tv_harga.setText("Rp " + String.format("%,d",total));
        Glide.with(context).load(Constanta.BASE_URL_IMG + dataTransaction.getFoto()).into(holder.imageProduct);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProduct;
        TextView tv_harga,tv_nama,tv_qty;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.imageProduct);
            tv_harga = itemView.findViewById(R.id.tv_harga);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_qty = itemView.findViewById(R.id.tv_qty);
        }
    }
}
