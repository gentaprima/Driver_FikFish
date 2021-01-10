package com.example.fikfishdriver.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fikfishdriver.R;
import com.example.fikfishdriver.session.SystemDataLocal;
import com.example.fikfishdriver.ui.profile.activity.ChangeEmailActivity;
import com.example.fikfishdriver.ui.profile.activity.ChangePasswordActivity;
import com.example.fikfishdriver.ui.profile.activity.ChangeProfileActivity;
import com.example.fikfishdriver.ui.users.LoginActivity;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    TextView tv_nama,tv_email,tv_logout;
    private SystemDataLocal systemDataLocal;
    CardView cardViewProfile,cardViewPassword,cardViewEmail;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_email = view.findViewById(R.id.tv_email);
        tv_nama = view.findViewById(R.id.tv_nama);
        tv_logout = view.findViewById(R.id.tv_logout);
        cardViewEmail = view.findViewById(R.id.cardViewEmail);
        cardViewProfile = view.findViewById(R.id.cardViewProfile);
        cardViewPassword = view.findViewById(R.id.cardViewPassword);
        systemDataLocal = new SystemDataLocal(getContext());
        tv_email.setText(systemDataLocal.getLoginData().getEmail());
        tv_nama.setText(systemDataLocal.getLoginData().getFull_name());

        String email = systemDataLocal.getLoginData().getEmail();
        try {
            tv_email.setText(maskString(email,3,7,'*'));
        } catch (Exception e) {
            e.printStackTrace();
        }

        tv_logout.setOnClickListener(this);
        cardViewProfile.setOnClickListener(this);
        cardViewPassword.setOnClickListener(this);
        cardViewEmail.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        tv_nama.setText(systemDataLocal.getLoginData().getFull_name());
        String email = systemDataLocal.getLoginData().getEmail();
        try {
            tv_email.setText(maskString(email,3,7,'*'));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    private static String maskString(String strText, int start, int end, char maskChar)
            throws Exception{

        if(strText == null || strText.equals(""))
            return "";

        if(start < 0)
            start = 0;

        if( end > strText.length() )
            end = strText.length();

        if(start > end)
            throw new Exception("End index cannot be greater than start index");

        int maskLength = end - start;

        if(maskLength == 0)
            return strText;

        StringBuilder sbMaskString = new StringBuilder(maskLength);

        for(int i = 0; i < maskLength; i++){
            sbMaskString.append(maskChar);
        }

        return strText.substring(0, start)
                + sbMaskString.toString()
                + strText.substring(start + maskLength);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_logout){
            systemDataLocal.destroySession();
            Intent logout = new Intent(getContext(), LoginActivity.class);
            startActivity(logout);
        }else if(v.getId() == R.id.cardViewProfile){
            startActivity(new Intent(getContext(), ChangeProfileActivity.class));
        }else if(v.getId() == R.id.cardViewEmail){
            startActivity(new Intent(getContext(), ChangeEmailActivity.class));
        }else if(v.getId() == R.id.cardViewPassword){
            startActivity(new Intent(getContext(), ChangePasswordActivity.class));
        }
    }
}