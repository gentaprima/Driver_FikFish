package com.example.fikfishdriver.model.shippinng;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShippingResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data_shipping")
    @Expose
    private List<DataShipping> dataShipping = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<DataShipping> getDataShipping() {
        return dataShipping;
    }

    public void setDataShipping(List<DataShipping> dataShipping) {
        this.dataShipping = dataShipping;
    }
}
