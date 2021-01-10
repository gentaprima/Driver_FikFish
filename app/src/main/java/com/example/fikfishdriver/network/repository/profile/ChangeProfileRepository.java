package com.example.fikfishdriver.network.repository.profile;

import androidx.lifecycle.MutableLiveData;

import com.example.fikfishdriver.model.MessageOnly;
import com.example.fikfishdriver.network.api.ApiClient;
import com.example.fikfishdriver.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeProfileRepository  {

    private ApiInterface apiInterface;

    public ChangeProfileRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<MessageOnly> changeProfile(String username,String full_name,String no_hp){
        final MutableLiveData<MessageOnly> mutableLiveData = new MutableLiveData<>();
        Call<MessageOnly> requestOrder = apiInterface.changeProfile(username,full_name,no_hp);
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
