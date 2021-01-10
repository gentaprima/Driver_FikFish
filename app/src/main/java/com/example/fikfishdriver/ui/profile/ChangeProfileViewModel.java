package com.example.fikfishdriver.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fikfishdriver.model.MessageOnly;
import com.example.fikfishdriver.network.repository.profile.ChangeProfileRepository;

public class ChangeProfileViewModel extends ViewModel {
    private ChangeProfileRepository changeProfileRepository;

    public ChangeProfileViewModel(){
        changeProfileRepository = new ChangeProfileRepository();
    }

    public LiveData<MessageOnly> changeProfile(String username,String full_name,String no_hp){
        return  changeProfileRepository.changeProfile(username,full_name,no_hp);
    }
}
