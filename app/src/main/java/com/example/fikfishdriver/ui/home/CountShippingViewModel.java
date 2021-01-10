package com.example.fikfishdriver.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fikfishdriver.model.home.CountResponse;
import com.example.fikfishdriver.network.repository.home.CountShippingRepository;

public class CountShippingViewModel extends ViewModel {
    private CountShippingRepository countShippingRepository;

    public CountShippingViewModel(){
        countShippingRepository = new CountShippingRepository();
    }

    public LiveData<CountResponse> getCountshipping(String id_courier,String date){
        return countShippingRepository.getDataCount(id_courier,date);
    }
}
