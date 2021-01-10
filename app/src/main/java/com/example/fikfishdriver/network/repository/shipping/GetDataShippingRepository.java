package com.example.fikfishdriver.network.repository.shipping;

import androidx.lifecycle.MutableLiveData;

import com.example.fikfishdriver.model.shippinng.DataShipping;
import com.example.fikfishdriver.model.shippinng.ShippingResponse;
import com.example.fikfishdriver.network.api.ApiClient;
import com.example.fikfishdriver.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataShippingRepository {
    private ApiInterface apiInterface;

    public GetDataShippingRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<ShippingResponse> getDataShipping(String id_courier,String latitude,String longitude,String date){
        final MutableLiveData<ShippingResponse> mutableLiveData = new MutableLiveData<>();
        Call<ShippingResponse> requestOrder = apiInterface.getDataShipping(id_courier,latitude,longitude,date);
        requestOrder.enqueue(new Callback<ShippingResponse>() {
            @Override
            public void onResponse(Call<ShippingResponse> call, Response<ShippingResponse> response) {
                if(response.body() != null){
                    mutableLiveData.postValue(response.body());
                }else{
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ShippingResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
