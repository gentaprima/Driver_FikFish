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
import com.example.fikfishdriver.ui.profile.ChangePasswordViewModel;
import com.example.fikfishdriver.utils.DialogClass;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edt_oldpassword,edt_newpassword,edt_confirm;
    Button btn_submit;
    private SystemDataLocal systemDataLocal;
    private ChangePasswordViewModel changePasswordViewModel;
    private android.app.AlertDialog alertDialog;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        edt_oldpassword = findViewById(R.id.edt_oldpassword);
        edt_newpassword = findViewById(R.id.edt_newpassword);
        edt_confirm = findViewById(R.id.edt_confirm);
        btn_submit = findViewById(R.id.btn_submit);

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
        changePasswordViewModel = ViewModelProviders.of(this).get(ChangePasswordViewModel.class);
        tv_title.setText("Form Ubah Password");
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_submit){
            changePassword();
        }
    }

    private void changePassword() {
        String username = systemDataLocal.getLoginData().getUsername();
        String oldPassword = edt_oldpassword.getText().toString();
        String newPassword = edt_newpassword.getText().toString();
        String confirmPassword = edt_confirm.getText().toString();

        View myview = getLayoutInflater().inflate(R.layout.loading_alert,null,false);
        alertDialog = DialogClass.dialog(this,myview).create();
        alertDialog.show();

        changePasswordViewModel.changePassword(username,oldPassword,newPassword,confirmPassword).observe(this, new Observer<MessageOnly>() {
            @Override
            public void onChanged(MessageOnly messageOnly) {
                if(messageOnly.getStatus()){

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