package com.example.fikfishdriver.ui.profile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fikfishdriver.R;
import com.example.fikfishdriver.model.MessageOnly;
import com.example.fikfishdriver.session.SystemDataLocal;
import com.example.fikfishdriver.ui.profile.ChangeEmailViewModel;
import com.example.fikfishdriver.utils.DialogClass;

public class ChangeEmailActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edt_oldemail,edt_newemail,edt_konfirm;
    Button btnSubmit;
    private android.app.AlertDialog alertDialog;
    private SystemDataLocal systemDataLocal;
    private ChangeEmailViewModel changeEmailViewModel;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        edt_oldemail = findViewById(R.id.edt_oldemail);
        edt_newemail = findViewById(R.id.edt_newemail);
        edt_konfirm = findViewById(R.id.edt_konfirm);
        btnSubmit = findViewById(R.id.btn_submit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView tv_title = findViewById(R.id.title);
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
        systemDataLocal = new SystemDataLocal(this);
        changeEmailViewModel = ViewModelProviders.of(this).get(ChangeEmailViewModel.class);
        tv_title.setText("Form Ubah Email");
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_submit){
            changeEmail();
        }
    }

    private void changeEmail() {
        String username = systemDataLocal.getLoginData().getUsername();
        String oldEmail = edt_oldemail.getText().toString();
        final String newEmail = edt_newemail.getText().toString();
        String confirmEmail = edt_konfirm.getText().toString();

        View myview = getLayoutInflater().inflate(R.layout.loading_alert,null,false);
        alertDialog = DialogClass.dialog(this,myview).create();
        alertDialog.show();

        changeEmailViewModel.changeEmail(username,oldEmail,newEmail,confirmEmail).observe(this, new Observer<MessageOnly>() {
            @Override
            public void onChanged(MessageOnly messageOnly) {
                if(messageOnly.getStatus()){
                    systemDataLocal.editAllSessionLogin(systemDataLocal.getLoginData().getId_kurir(),
                            systemDataLocal.getLoginData().getUsername(),
                            systemDataLocal.getLoginData().getFull_name(),
                            systemDataLocal.getLoginData().getPassword(),
                            newEmail,
                            systemDataLocal.getLoginData().getFoto(),
                            systemDataLocal.getLoginData().getPhone());
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