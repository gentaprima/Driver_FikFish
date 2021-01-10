package com.example.fikfishdriver.ui.profile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fikfishdriver.R;
import com.example.fikfishdriver.model.MessageOnly;
import com.example.fikfishdriver.session.SystemDataLocal;
import com.example.fikfishdriver.ui.profile.ChangeProfileViewModel;
import com.example.fikfishdriver.utils.DialogClass;

public class ChangeProfileActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextView tv_title;
    ImageView iv_cart;
    SystemDataLocal systemDataLocal;
    Button btn_submit;
    private android.app.AlertDialog alertDialog;
    String id_users;
    private ChangeProfileViewModel changeProfileViewModel;

    EditText edt_username,edt_full_name,edt_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);

        toolbar = findViewById(R.id.toolbar);
        tv_title = findViewById(R.id.title);
        edt_username = findViewById(R.id.edt_username);
        edt_full_name = findViewById(R.id.edt_fullname);
        edt_phone = findViewById(R.id.edt_phone);
        setSupportActionBar(toolbar);
        btn_submit = findViewById(R.id.btn_submit);
        systemDataLocal = new SystemDataLocal(this);

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
        tv_title.setText("Profile");
        edt_username.setText(systemDataLocal.getLoginData().getUsername());
        edt_full_name.setText(systemDataLocal.getLoginData().getFull_name());
        edt_phone.setText(systemDataLocal.getLoginData().getPhone());
        id_users = systemDataLocal.getLoginData().getId_kurir();

        changeProfileViewModel = ViewModelProviders.of(this).get(ChangeProfileViewModel.class);

        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_submit){
            changeProfile();
        }
    }

    private void changeProfile() {
        String username = edt_username.getText().toString();
        final String full_name = edt_full_name.getText().toString();
        final String no_hp = edt_phone.getText().toString();

        View myview = getLayoutInflater().inflate(R.layout.loading_alert,null,false);
        alertDialog = DialogClass.dialog(this,myview).create();
        alertDialog.show();

        changeProfileViewModel.changeProfile(username,full_name,no_hp).observe(this, new Observer<MessageOnly>() {
            @Override
            public void onChanged(MessageOnly messageOnly) {
                if(messageOnly.getStatus()){
                    systemDataLocal.editAllSessionLogin(systemDataLocal.getLoginData().getId_kurir(),
                                                        systemDataLocal.getLoginData().getUsername(),
                                                        full_name,
                                                        systemDataLocal.getLoginData().getPassword(),
                                                        systemDataLocal.getLoginData().getEmail(),
                                                        systemDataLocal.getLoginData().getFoto(),
                                                        no_hp);
                    alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }else{
                    alertDialog.dismiss();
                    Toast.makeText(getApplicationContext(),messageOnly.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}