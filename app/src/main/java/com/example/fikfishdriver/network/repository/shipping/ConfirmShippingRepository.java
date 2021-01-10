package com.example.fikfishdriver.network.repository.shipping;

import androidx.lifecycle.MutableLiveData;

import com.example.fikfishdriver.model.MessageOnly;
import com.example.fikfishdriver.network.api.ApiClient;
import com.example.fikfishdriver.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmShippingRepository {
    private ApiInterface apiInterface;

    public ConfirmShippingRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<MessageOnly> confirmShipping(String id_shipping,String receiver,String order_id){
        final MutableLiveData<MessageOnly> mutableLiveData = new MutableLiveData<>();
        Call<MessageOnly> requestOrder = apiInterface.acceptedShipping(id_shipping,receiver,order_id);
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
        return mutableLiveData;
    }
}
