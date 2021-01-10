package com.example.fikfishdriver.ui.shipping.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fikfishdriver.R;
import com.example.fikfishdriver.adapter.AdapterOrder;
import com.example.fikfishdriver.model.MessageOnly;
import com.example.fikfishdriver.model.order_transaction.DataTransaction;
import com.example.fikfishdriver.model.order_transaction.TransactionResponse;
import com.example.fikfishdriver.model.shippinng.DataShipping;
import com.example.fikfishdriver.ui.shipping.AddDelayShippingViewModel;
import com.example.fikfishdriver.ui.shipping.ChangeStatusDelayViewModel;
import com.example.fikfishdriver.ui.shipping.GetDataOrderViewModel;
import com.example.fikfishdriver.utils.DialogClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class DetailDelayShippingActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    Button btn_lanjut;
    TextView title,tv_nama,tv_jarak,tv_alamat,tv_biaya,tv_kirimkembali;
    private DataShipping dataShipping;
    RecyclerView rv_pesanan;
    private GetDataOrderViewModel getDataOrderViewModel;
    private android.app.AlertDialog alertDialog;
    private ChangeStatusDelayViewModel changeStatusDelayViewModel;
    AlertDialog.Builder builder;
    View myview;
    ImageView imageMaps,imagePhone,imageSms;
    private Double latitude,longitude;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_delay_shipping);
        toolbar = findViewById(R.id.toolbar);
        title = findViewById(R.id.title);
        tv_nama = findViewById(R.id.tv_nama);
        tv_jarak = findViewById(R.id.tv_jarak);
        tv_alamat = findViewById(R.id.tv_alamat);
        tv_biaya = findViewById(R.id.tv_biaya);
        rv_pesanan = findViewById(R.id.rv_pesanan);
        btn_lanjut = findViewById(R.id.btn_lanjut);
        imageMaps  = findViewById(R.id.imageMaps);
        imagePhone  = findViewById(R.id.imagePhone);
        imageSms  = findViewById(R.id.imageSms);
        tv_kirimkembali  = findViewById(R.id.tv_kirimkembali);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        dataShipping = getIntent().getParcelableExtra("data");
        title.setText("Detail Penundaan");

        tv_nama.setText(dataShipping.getFullName());
        String jarak = dataShipping.getJarak();
        tv_jarak.setText(jarak.substring(0,3) + "KM");
        tv_biaya.setText("Rp " + String.format("%,d",Integer.parseInt(dataShipping.getAdditionalCosts())));
        tv_alamat.setText(dataShipping.getAlamat());
        getDataOrderViewModel = ViewModelProviders.of(this).get(GetDataOrderViewModel.class);
        changeStatusDelayViewModel = ViewModelProviders.of(this).get(ChangeStatusDelayViewModel.class);

        loadDataOrder();
        btn_lanjut.setOnClickListener(this);
        imageMaps.setOnClickListener(this);
        imagePhone.setOnClickListener(this);
        imageSms.setOnClickListener(this);
        latitude = -6.229460;
        longitude = 106.884471;
        if(dataShipping.getStatus().equals("Menunggu Pengiriman")){
            String dateShipping = dataShipping.getDateShipping();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format2 = new SimpleDateFormat("dd MMMM yyyy");
            try {
                String date2 = format2.format(Objects.requireNonNull(format1.parse(dateShipping)));
                tv_kirimkembali.setText("Silahkan lanjutkan pengiriman pada tanggal " + date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

    }

    private void loadDataOrder() {
        getDataOrderViewModel.getDataOrder(dataShipping.getOrderId()).observe(this, new Observer<TransactionResponse>() {
            @Override
            public void onChanged(TransactionResponse transactionResponse) {
                if(transactionResponse.getStatus()){
                    if(transactionResponse.getData().size() > 0){
                        readData(transactionResponse.getData());
                    }
                }
            }
        });
    }

    private void readData(List<DataTransaction> data) {
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        AdapterOrder adapterOrder = new AdapterOrder(this,data);
        rv_pesanan.setAdapter(adapterOrder);
        rv_pesanan.setLayoutManager(lm);
        rv_pesanan.setNestedScrollingEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataOrder();
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_lanjut){
            dialogDelay();
        }

        switch (v.getId()){
            case R.id.btn_lanjut:
                dialogDelay();
                break;
            case R.id.imageMaps:
                Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/"+latitude+","+longitude+"/"+dataShipping.getLatitude()+","+dataShipping.getLongtitude()+"/");
                Intent mapIntent = new  Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;

            case R.id.imageSms:
                String number = dataShipping.getNoHp();  // The number on which you want to send SMS
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
                break;

            case R.id.imagePhone:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+dataShipping.getNoHp()));
                startActivity(intent);
//                String url = "https://api.whatsapp.com/send?phone=+62"+dataShipping.getNoHp();
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
                break;
        }
    }

    private void changeStatusDelay() {
        String id_shipping = dataShipping.getIdShipping();
        String dateShipping = dataShipping.getDateShipping();

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = df.format(c);

        View v = getLayoutInflater().inflate(R.layout.loading_alert,null,false);
        alertDialog = DialogClass.dialog(this,v).create();
        alertDialog.show();
        if(dataShipping.getStatus().equals("Ditunda")){
            alertDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Silahkan tunggu jadwal pengiriman kembali oleh pemesan",Toast.LENGTH_LONG).show();
        }else if(dateShipping.equals(formattedDate)){
            changeStatusDelayViewModel.changeStatusDelay(id_shipping).observe(this, new Observer<MessageOnly>() {
                @Override
                public void onChanged(MessageOnly messageOnly) {
                    if(messageOnly.getStatus()){
                        alertDialog.dismiss();
                        Toast.makeText(getApplicationContext(),messageOnly.getMessage(), Toast.LENGTH_LONG).show();
                        onBackPressed();
                    }
                }
            });
        }else{
            alertDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Silahkan lanjutkan pengiriman pada tanggal yang ditentukan !",Toast.LENGTH_LONG).show();
        }

    }

    @SuppressLint("SetTextI18n")
    private void dialogDelay() {
        builder = new AlertDialog.Builder(this);
        myview = getLayoutInflater().inflate(R.layout.dialog_delay,null,false);
        builder.setView(myview);
        builder.setTitle("Form Lanjut Pengiriman");
        builder.setCancelable(true);
        TextView tv_alert = myview.findViewById(R.id.tv_alert);
        tv_alert.setText("Anda Yakin ingin melanjutkan pengiriman hari ini ?");
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Lanjutkan Pengiriman", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeStatusDelay();
            }
        });

        builder.show();
    }
}