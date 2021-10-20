package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VoucherPrint4Response implements Serializable {

    @SerializedName("TotalTillDate")
    @Expose
    private Integer totalTillDate;
    @SerializedName("LifeHeldupAmt")
    @Expose
    private Integer lifeHeldupAmt;
    @SerializedName("PhaseName")
    @Expose
    private String phaseName;
    @SerializedName("Status")
    @Expose
    private String status;

    public Integer getTotalTillDate() {
        return totalTillDate;
    }

    public void setTotalTillDate(Integer totalTillDate) {
        this.totalTillDate = totalTillDate;
    }

    public Integer getLifeHeldupAmt() {
        return lifeHeldupAmt;
    }

    public void setLifeHeldupAmt(Integer lifeHeldupAmt) {
        this.lifeHeldupAmt = lifeHeldupAmt;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
