package com.example.fikfishdriver.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fikfishdriver.model.MessageOnly;
import com.example.fikfishdriver.network.repository.profile.ChangePasswordRepository;

public class ChangePasswordViewModel extends ViewModel {

    private ChangePasswordRepository changePasswordRepository;

    public ChangePasswordViewModel(){
        changePasswordRepository=  new ChangePasswordRepository();
    }

    public LiveData<MessageOnly> changePassword(String username,String oldPassword,String newPassword,String confirmPassword){
        return changePasswordRepository.changePassword(username,oldPassword,newPassword,confirmPassword);
    }
}
