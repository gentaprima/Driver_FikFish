package com.example.fikfishdriver.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fikfishdriver.model.history.HistoryResponse;
import com.example.fikfishdriver.network.repository.history.GetDataHistoryByDateRepository;

public class GetDataHistoryDetailViewModel extends ViewModel {
    private GetDataHistoryByDateRepository getDataHistoryByDateRepository;

    public GetDataHistoryDetailViewModel(){
        getDataHistoryByDateRepository = new GetDataHistoryByDateRepository();
    }

    public LiveData<HistoryResponse> getDataHistoryDetail(String id_courier,String date){
        return getDataHistoryByDateRepository.getDataHistoryByDate(id_courier,date);
    }
}
