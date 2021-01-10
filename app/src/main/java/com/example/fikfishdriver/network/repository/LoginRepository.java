package com.example.fikfishdriver.network.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.fikfishdriver.model.users.LoginResponse;
import com.example.fikfishdriver.network.api.ApiClient;
import com.example.fikfishdriver.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private ApiInterface apiInterface;

    public LoginRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<LoginResponse> loginCourier(String username,String password){
        final MutableLiveData<LoginResponse> mutableLiveData = new MutableLiveData<>();
        Call<LoginResponse> requestOrder = apiInterface.loginUser(username,password);
        requestOrder.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.body() != null){
                    mutableLiveData.postValue(response.body());
                }else{
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
