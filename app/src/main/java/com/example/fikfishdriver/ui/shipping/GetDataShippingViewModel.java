package com.example.fikfishdriver.ui.shipping;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fikfishdriver.model.shippinng.ShippingResponse;
import com.example.fikfishdriver.network.repository.shipping.GetDataShippingRepository;

public class GetDataShippingViewModel extends ViewModel {
    private GetDataShippingRepository getDataShippingRepository;

    public GetDataShippingViewModel(){
        getDataShippingRepository = new GetDataShippingRepository();
    }

    public LiveData<ShippingResponse> getDataShipping(String id_courier,String latitude,String longitude,String date){
        return getDataShippingRepository.getDataShipping(id_courier,latitude,longitude,date);
    }
}
