package com.example.fikfishdriver.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fikfishdriver.R;
import com.example.fikfishdriver.model.home.CountResponse;
import com.example.fikfishdriver.session.SystemDataLocal;
import com.example.fikfishdriver.ui.home.CountShippingViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class HomeFragment extends Fragment {

    private SystemDataLocal systemDataLocal;
    private CountShippingViewModel countShippingViewModel;
    private TextView tv_countshipping,tv_countpenundaan,tv_nama;

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_countpenundaan = view.findViewById(R.id.tv_jumlahpenundaan);
        tv_countshipping = view.findViewById(R.id.tv_jumlahpengiriman);
        tv_nama = view.findViewById(R.id.tv_nama);
        systemDataLocal = new SystemDataLocal(getContext());
        countShippingViewModel = ViewModelProviders.of(this).get(CountShippingViewModel.class);
        loadCount();

        String full_name = systemDataLocal.getLoginData().getFull_name();
        String[] parts = full_name.split(" ");
        tv_nama.setText("Halo, " + parts[0]);

    }

    private void loadCount() {
        String id_courier = systemDataLocal.getLoginData().getId_kurir();
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = df.format(c);
        countShippingViewModel.getCountshipping(id_courier,formattedDate).observe(this, new Observer<CountResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(CountResponse countResponse) {
                if(countResponse.getStatus()){
                    String countShipping = String.valueOf(countResponse.getCountShipping());
                    String countDone = String.valueOf(countResponse.getCountDone());
                    tv_countshipping.setText(countDone + " / "+countShipping);
                    tv_countpenundaan.setText(String.valueOf(countResponse.getCountDelay()));
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCount();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}