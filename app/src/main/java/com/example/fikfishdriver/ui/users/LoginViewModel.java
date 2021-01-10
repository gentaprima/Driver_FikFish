package com.example.fikfishdriver.ui.users;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fikfishdriver.model.users.LoginResponse;
import com.example.fikfishdriver.network.repository.LoginRepository;

public class LoginViewModel extends ViewModel {
    private LoginRepository loginRepository;

    public LoginViewModel(){
        loginRepository = new LoginRepository();
    }

    public LiveData<LoginResponse> loginCourier(String username,String password){
        return loginRepository.loginCourier(username,password);
    }
}
