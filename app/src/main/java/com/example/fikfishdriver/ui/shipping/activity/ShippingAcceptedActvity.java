
package com.example.fikfishdriver.ui.shipping.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fikfishdriver.R;
import com.example.fikfishdriver.model.MessageOnly;
import com.example.fikfishdriver.model.shippinng.DataShipping;
import com.example.fikfishdriver.ui.MainActivity;
import com.example.fikfishdriver.ui.shipping.ConfirmShippingViewModel;
import com.example.fikfishdriver.utils.DialogClass;

public class ShippingAcceptedActvity extends AppCompatActivity {

    Toolbar toolbar;
    EditText edt_penerima;
    Button btn_submit;
    TextView tv_title;
    private DataShipping dataShipping;
    private ConfirmShippingViewModel confirmShippingViewModel;
    private android.app.AlertDialog alertDialog;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_accepted);
        toolbar = findViewById(R.id.toolbar);
        tv_title = findViewById(R.id.title);
        edt_penerima = findViewById(R.id.edt_penerima);
        btn_submit = findViewById(R.id.btn_submit);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        tv_title.setText("Konfirmasi Pengiriman");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        dataShipping = getIntent().getParcelableExtra("data");
        confirmShippingViewModel = ViewModelProviders.of(this).get(ConfirmShippingViewModel.class);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmShipping();
            }
        });


    }

    private void confirmShipping() {
        View v = getLayoutInflater().inflate(R.layout.loading_alert,null,false);
        String id_shipping = dataShipping.getIdShipping();
        String id_order = dataShipping.getOrderId();
        String receiver = edt_penerima.getText().toString();
        alertDialog = DialogClass.dialog(this,v).create();
        alertDialog.show();

        confirmShippingViewModel.confirmShipping(id_shipping,receiver,id_order).observe(this, new Observer<MessageOnly>() {
            @Override
            public void onChanged(MessageOnly messageOnly) {
                if(messageOnly.getStatus()){
                    alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ShippingAcceptedActvity.this, MainActivity.class));
                    finish();
                }else{
                    alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}