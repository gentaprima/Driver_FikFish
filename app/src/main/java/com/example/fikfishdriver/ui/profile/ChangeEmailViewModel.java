package com.example.fikfishdriver.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fikfishdriver.model.MessageOnly;
import com.example.fikfishdriver.network.repository.profile.ChangeEmailRepository;

public class ChangeEmailViewModel extends ViewModel {
    private ChangeEmailRepository changeEmailRepository;

    public ChangeEmailViewModel(){
        changeEmailRepository = new ChangeEmailRepository();
    }

    public LiveData<MessageOnly> changeEmail(String username,String oldEmail,String newEmail,String confirmEmail){
        return changeEmailRepository.changeEmail(username,oldEmail,newEmail,confirmEmail);
    }
}
