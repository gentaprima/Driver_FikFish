package com.example.fikfishdriver.network.repository.history;

import androidx.lifecycle.MutableLiveData;

import com.example.fikfishdriver.model.history.HistoryResponse;
import com.example.fikfishdriver.network.api.ApiClient;
import com.example.fikfishdriver.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataHistoryByDateRepository {
    private ApiInterface apiInterface;

    public GetDataHistoryByDateRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<HistoryResponse> getDataHistoryByDate(String id_courier,String date){
        final MutableLiveData<HistoryResponse> mutableLiveData = new MutableLiveData<>();
        Call<HistoryResponse> requestOrder = apiInterface.getDataHistoryByDate(id_courier,date);
        requestOrder.enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                if(response.body() != null){
                    mutableLiveData.postValue(response.body());
                }else{
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return  mutableLiveData;
    }
}
