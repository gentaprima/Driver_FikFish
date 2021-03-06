package com.example.fikfishdriver.network.repository.shipping;

import androidx.lifecycle.MutableLiveData;

import com.example.fikfishdriver.model.MessageOnly;
import com.example.fikfishdriver.network.api.ApiClient;
import com.example.fikfishdriver.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDelayShippingRepository {
    private ApiInterface apiInterface;

    public AddDelayShippingRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<MessageOnly> addShippingDelay(String id_shipping){
        final MutableLiveData<MessageOnly> mutableLiveData = new MutableLiveData<>();
        Call<MessageOnly> requestOrder = apiInterface.addDelayShipping(id_shipping);
        requestOrder.enqueue(new Callback<MessageOnly>() {
            @Override
            public void onResponse(Call<MessageOnly> call, Response<MessageOnly> response) {
                if(response.body() != null){
                    mutableLiveData.postValue(response.body());
                }else{
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<MessageOnly> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return  mutableLiveData;
    }
}
