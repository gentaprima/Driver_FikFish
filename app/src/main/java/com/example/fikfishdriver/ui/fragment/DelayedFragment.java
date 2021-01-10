package com.example.fikfishdriver.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fikfishdriver.R;
import com.example.fikfishdriver.adapter.AdapterShippingDelay;
import com.example.fikfishdriver.model.shippinng.DataShipping;
import com.example.fikfishdriver.model.shippinng.ShippingResponse;
import com.example.fikfishdriver.session.SystemDataLocal;
import com.example.fikfishdriver.ui.shipping.GetDataShippingDelayViewModel;

import java.util.List;

public class DelayedFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView rv_shipping;
    private SystemDataLocal systemDataLocal;
    private GetDataShippingDelayViewModel getDataShippingDelayViewModel;
    Double latitude,longitude;
    SwipeRefreshLayout refreshLayout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_shipping = view.findViewById(R.id.rv_shipping);
        systemDataLocal = new SystemDataLocal(getContext());
        getDataShippingDelayViewModel = ViewModelProviders.of(this).get(GetDataShippingDelayViewModel.class);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        latitude = -6.229460;
        longitude = 106.884471;
        loadDataShipping();

        refreshLayout.setOnRefreshListener(this);
    }

    private void loadDataShipping() {
        String id_courier = systemDataLocal.getLoginData().getId_kurir();
        getDataShippingDelayViewModel.getDataShippingDelayed(id_courier,String.valueOf(latitude),String.valueOf(longitude)).observe(this, new Observer<ShippingResponse>() {
            @Override
            public void onChanged(ShippingResponse shippingResponse) {
                if(shippingResponse.getStatus()){
                    if(shippingResponse.getDataShipping().size() > 0){
                        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
                        AdapterShippingDelay adapterShippingDelay  = new AdapterShippingDelay(getContext(),shippingResponse.getDataShipping());
                        rv_shipping.setLayoutManager(lm);
                        rv_shipping.setAdapter(adapterShippingDelay);
                        rv_shipping.setVisibility(View.VISIBLE);
                    }else{
                        rv_shipping.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void readData(List<DataShipping> dataShipping) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delayed, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDataShipping();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadDataShipping();
                refreshLayout.setRefreshing(false);
            }
        },1000);
    }
}