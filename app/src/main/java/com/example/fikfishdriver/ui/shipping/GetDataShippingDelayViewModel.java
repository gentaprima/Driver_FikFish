package com.example.fikfishdriver.ui.shipping;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fikfishdriver.model.shippinng.ShippingResponse;
import com.example.fikfishdriver.network.repository.shipping.GetDataShippingDelayRepository;

public class GetDataShippingDelayViewModel extends ViewModel {
    private GetDataShippingDelayRepository getDataShippingDelayRepository;

    public GetDataShippingDelayViewModel(){
        getDataShippingDelayRepository = new GetDataShippingDelayRepository();
    }

    public LiveData<ShippingResponse> getDataShippingDelayed(String id_courier,String latitude,String longitude){
        return getDataShippingDelayRepository.getDataShippingDelayed(id_courier,latitude,longitude);
    }
}
