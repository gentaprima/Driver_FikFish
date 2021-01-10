package com.example.fikfishdriver.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fikfishdriver.model.history.HistoryResponse;
import com.example.fikfishdriver.network.repository.history.GetDataHistoryRepository;

public class GetDataHistoryViewModel extends ViewModel {
    private GetDataHistoryRepository getDataHistoryRepository;

    public GetDataHistoryViewModel(){
        getDataHistoryRepository = new GetDataHistoryRepository();
    }

    public LiveData<HistoryResponse> getDataHistory(String id_courier){
        return  getDataHistoryRepository.getDataHistory(id_courier);
    }
}
