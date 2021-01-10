package com.example.fikfishdriver.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("count_shipping")
    @Expose
    private Integer countShipping;
    @SerializedName("count_delay")
    @Expose
    private Integer countDelay;
    @SerializedName("count_shipping_done")
    private Integer countDone;

    public Integer getCountDone() {
        return countDone;
    }

    public void setCountDone(Integer countDone) {
        this.countDone = countDone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCountShipping() {
        return countShipping;
    }

    public void setCountShipping(Integer countShipping) {
        this.countShipping = countShipping;
    }

    public Integer getCountDelay() {
        return countDelay;
    }

    public void setCountDelay(Integer countDelay) {
        this.countDelay = countDelay;
    }
}
