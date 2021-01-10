package com.example.fikfishdriver.ui.shipping;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fikfishdriver.model.MessageOnly;
import com.example.fikfishdriver.network.repository.shipping.AddDelayShippingRepository;

public class AddDelayShippingViewModel extends ViewModel {
    private AddDelayShippingRepository addDelayShippingRepository;

    public AddDelayShippingViewModel(){
        addDelayShippingRepository = new AddDelayShippingRepository();
    }

    public LiveData<MessageOnly> addShippingDelay(String id_shipping){
        return addDelayShippingRepository.addShippingDelay(id_shipping);
    }
}
