package com.example.fikfishdriver.ui.shipping;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fikfishdriver.model.order_transaction.TransactionResponse;
import com.example.fikfishdriver.network.repository.order.GetDataOrderRepository;

public class GetDataOrderViewModel extends ViewModel {
    private GetDataOrderRepository getDataOrderRepository;

    public GetDataOrderViewModel(){
        getDataOrderRepository = new GetDataOrderRepository();
    }

    public LiveData<TransactionResponse> getDataOrder(String id_order){
        return getDataOrderRepository.getDataOrder(id_order);
    }
}
