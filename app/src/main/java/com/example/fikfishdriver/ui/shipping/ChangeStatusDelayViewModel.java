package com.example.fikfishdriver.ui.shipping;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fikfishdriver.model.MessageOnly;
import com.example.fikfishdriver.network.repository.shipping.ChangeStatusDelayRepository;

public class ChangeStatusDelayViewModel extends ViewModel {
    private ChangeStatusDelayRepository changeStatusDelayRepository;

    public ChangeStatusDelayViewModel(){
        changeStatusDelayRepository = new ChangeStatusDelayRepository();
    }

    public LiveData<MessageOnly> changeStatusDelay(String id_shipping){
        return changeStatusDelayRepository.changeStatusDelay(id_shipping);
    }
}
