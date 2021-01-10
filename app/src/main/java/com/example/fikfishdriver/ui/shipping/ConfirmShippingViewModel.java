package com.example.fikfishdriver.ui.shipping;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fikfishdriver.model.MessageOnly;
import com.example.fikfishdriver.network.repository.shipping.ConfirmShippingRepository;

public class ConfirmShippingViewModel extends ViewModel {
    private ConfirmShippingRepository confirmShippingRepository;

    public ConfirmShippingViewModel(){
        confirmShippingRepository = new ConfirmShippingRepository();
    }

    public LiveData<MessageOnly> confirmShipping(String id_shipping,String receiver,String id_order){
        return confirmShippingRepository.confirmShipping(id_shipping,receiver,id_order);
    }
}
