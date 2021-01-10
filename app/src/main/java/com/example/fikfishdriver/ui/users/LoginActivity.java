package com.example.fikfishdriver.ui.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fikfishdriver.R;
import com.example.fikfishdriver.model.users.LoginResponse;
import com.example.fikfishdriver.session.SystemDataLocal;
import com.example.fikfishdriver.ui.MainActivity;
import com.example.fikfishdriver.utils.DialogClass;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private LoginViewModel loginViewModel;
    private EditText edt_usernmae,edt_password;
    private Button btn_login;
    private android.app.AlertDialog alertDialog;
    private SystemDataLocal systemDataLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_password = findViewById(R.id.edt_password);
        edt_usernmae = findViewById(R.id.edt_username);
        btn_login = findViewById(R.id.btn_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        systemDataLocal = new SystemDataLocal(this);
        if(systemDataLocal.getCheckLogin()){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
        
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                proccesLogin();
                break;
        }
    }

    private void proccesLogin() {
        String username = edt_usernmae.getText().toString();
        String password = edt_password.getText().toString();
        View v = getLayoutInflater().inflate(R.layout.loading_alert,null,false);

        alertDialog = DialogClass.dialog(this,v).create();
        alertDialog.show();

        loginViewModel.loginCourier(username,password).observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if(loginResponse != null){
                    if(loginResponse.getStatus()){
                        alertDialog.dismiss();
                        systemDataLocal = new SystemDataLocal(LoginActivity.this,loginResponse.getUserData());
                        systemDataLocal.setSessionLogin();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }else{
                        alertDialog.dismiss();
                        Toast.makeText(getApplicationContext(),loginResponse.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }else{
                    alertDialog.dismiss();
                }
            }
        });
    }
}