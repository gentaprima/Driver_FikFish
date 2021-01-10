package com.example.fikfishdriver.model.history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data_history")
    @Expose
    private List<DataHistory> dataHistory = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<DataHistory> getDataHistory() {
        return dataHistory;
    }

    public void setDataHistory(List<DataHistory> dataHistory) {
        this.dataHistory = dataHistory;
    }
}
