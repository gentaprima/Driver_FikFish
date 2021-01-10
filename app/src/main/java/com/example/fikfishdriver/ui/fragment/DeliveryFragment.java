package com.example.fikfishdriver.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.fikfishdriver.R;
import com.example.fikfishdriver.adapter.AdapterShipping;
import com.example.fikfishdriver.model.shippinng.ShippingResponse;
import com.example.fikfishdriver.session.SystemDataLocal;
import com.example.fikfishdriver.ui.shipping.GetDataShippingViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DeliveryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


   private SystemDataLocal systemDataLocal;
   private GetDataShippingViewModel getDataShippingViewModel;
   private String id_courier;
   private Double latitude,longitude;
   private RecyclerView rv_shipping;
   SwipeRefreshLayout refreshLayout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        systemDataLocal = new SystemDataLocal(getContext());
        getDataShippingViewModel = ViewModelProviders.of(this).get(GetDataShippingViewModel.class);
        id_courier = systemDataLocal.getLoginData().getId_kurir();
        latitude = -6.229460;
        longitude = 106.884471;
        rv_shipping = view.findViewById(R.id.rv_shipping);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        loadData();

        refreshLayout.setOnRefreshListener(this);
    }

    private void loadData() {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = df.format(c);
        getDataShippingViewModel.getDataShipping(id_courier,String.valueOf(latitude),String.valueOf(longitude),formattedDate).observe(this, new Observer<ShippingResponse>() {
            @Override
            public void onChanged(ShippingResponse shippingResponse) {
                if(shippingResponse != null){
                    if(shippingResponse.getDataShipping().size() > 0){
                        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
                        AdapterShipping adapterShipping = new AdapterShipping(getContext(),shippingResponse.getDataShipping());
                        rv_shipping.setLayoutManager(lm);
                        rv_shipping.setAdapter(adapterShipping);
                        rv_shipping.setVisibility(View.VISIBLE);
                    }else{
                        rv_shipping.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery, container, false);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                refreshLayout.setRefreshing(false);
            }
        },1000);
    }
}