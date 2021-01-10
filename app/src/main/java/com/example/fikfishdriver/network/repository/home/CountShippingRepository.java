package com.example.fikfishdriver.network.repository.home;

import androidx.lifecycle.MutableLiveData;

import com.example.fikfishdriver.model.home.CountResponse;
import com.example.fikfishdriver.network.api.ApiClient;
import com.example.fikfishdriver.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountShippingRepository {
    private ApiInterface apiInterface;

    public CountShippingRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<CountResponse> getDataCount(String id_courier,String date){
        final MutableLiveData<CountResponse> mutableLiveData = new MutableLiveData<>();
        Call<CountResponse> requestOrder = apiInterface.getCountShipping(id_courier,date);
        requestOrder.enqueue(new Callback<CountResponse>() {
            @Override
            public void onResponse(Call<CountResponse> call, Response<CountResponse> response) {
                if(response.body() != null){
                    mutableLiveData.postValue(response.body());
                }else{
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<CountResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
