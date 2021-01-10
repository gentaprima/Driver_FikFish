package com.example.fikfishdriver.network.repository.profile;

import androidx.lifecycle.MutableLiveData;

import com.example.fikfishdriver.model.MessageOnly;
import com.example.fikfishdriver.network.api.ApiClient;
import com.example.fikfishdriver.network.api.ApiInterface;

import java.security.MessageDigest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeEmailRepository {
    private ApiInterface apiInterface;

    public ChangeEmailRepository(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

    }

    public MutableLiveData<MessageOnly> changeEmail(String username,String oldEmail,String newEmail,String confirmEmail){
        final MutableLiveData<MessageOnly> mutableLiveData = new MutableLiveData<>();
        Call<MessageOnly> requestOrder = apiInterface.changeEmail(username,oldEmail,newEmail,confirmEmail);
        requestOrder.enqueue(new Callback<MessageOnly>() {
            @Override
            public void onResponse(Call<MessageOnly> call, Response<MessageOnly> response) {
                if(response != null){
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
