package com.example.fikfishdriver.ui.shipping.activity;

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
import android.os.Bundle;
import android.os.Handler;
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
import com.example.fikfishdriver.ui.shipping.GetDataOrderViewModel;
import com.example.fikfishdriver.utils.DialogClass;

import java.util.List;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class DetailShippingActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextView title,tv_nama,tv_jarak,tv_alamat,tv_biaya;
    private DataShipping dataShipping;
    RecyclerView rv_pesanan;
    private GetDataOrderViewModel getDataOrderViewModel;
    AlertDialog.Builder builder;
    private android.app.AlertDialog alertDialog;
    private AddDelayShippingViewModel addDelayShippingViewModel;
    Button btn_delay;
    View myview;
    ProSwipeButton slide_btn;
    ImageView imageMaps,imagePhone,imageSms;
    private Double latitude,longitude;
    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shipping);
        toolbar = findViewById(R.id.toolbar);
        title = findViewById(R.id.title);
        tv_nama = findViewById(R.id.tv_nama);
        tv_jarak = findViewById(R.id.tv_jarak);
        tv_alamat = findViewById(R.id.tv_alamat);
        rv_pesanan = findViewById(R.id.rv_pesanan);
        btn_delay = findViewById(R.id.btn_delay);
        tv_biaya = findViewById(R.id.tv_biaya);
        slide_btn  = findViewById(R.id.btn_slide);
        imageMaps  = findViewById(R.id.imageMaps);
        imagePhone  = findViewById(R.id.imagePhone);
        imageSms  = findViewById(R.id.imageSms);
        setSupportActionBar(toolbar);
        latitude = -6.229460;
        longitude = 106.884471;
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
        title.setText("Detail Pengiriman");

        tv_nama.setText(dataShipping.getFullName());
        String jarak = dataShipping.getJarak();
        tv_jarak.setText(jarak.substring(0,3) + "KM");
        tv_alamat.setText(dataShipping.getAlamat());
        tv_biaya.setText("Rp " + String.format("%,d",Integer.parseInt(dataShipping.getAdditionalCosts())));
        getDataOrderViewModel = ViewModelProviders.of(this).get(GetDataOrderViewModel.class);
        loadDataOrder();

        addDelayShippingViewModel = ViewModelProviders.of(this).get(AddDelayShippingViewModel.class);
        btn_delay.setOnClickListener(this);
        imageMaps.setOnClickListener(this);
        imagePhone.setOnClickListener(this);
        imageSms.setOnClickListener(this);

        slide_btn.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent accepted = new Intent(DetailShippingActivity.this,ShippingAcceptedActvity.class);
                        accepted.putExtra("data",dataShipping);
                        slide_btn.showResultIcon(true,true);
                        startActivity(accepted);
                    }
                }, 100);

            }
        });
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

        switch (v.getId()){
            case  R.id.btn_delay:
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

    private void dialogDelay() {
        builder = new AlertDialog.Builder(this);
        myview = getLayoutInflater().inflate(R.layout.dialog_delay,null,false);
        builder.setView(myview);
        builder.setTitle("Form Penundaan Pengiriman");
        builder.setCancelable(true);
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Tunda Pengiriman", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addDelay();
            }
        });

        builder.show();
    }

    private void addDelay() {
        String id_shipping = dataShipping.getIdShipping();
        View v = getLayoutInflater().inflate(R.layout.loading_alert,null,false);
        alertDialog = DialogClass.dialog(this,v).create();
        alertDialog.show();
        addDelayShippingViewModel.addShippingDelay(id_shipping).observe(this, new Observer<MessageOnly>() {
            @Override
            public void onChanged(MessageOnly messageOnly) {
                if(messageOnly.getStatus()){
                    alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_LONG).show();
                    onBackPressed();
                }
            }
        });
    }
}