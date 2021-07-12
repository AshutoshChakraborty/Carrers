package com.project.integratedservices.repository.integratedServicesForAllTypes.response.message_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SmsDetailsResposne {

    @SerializedName("SlNo")
    @Expose
    private String slNo;
    @SerializedName("SMS")
    @Expose
    private String sms;
    @SerializedName("SMSDate")
    @Expose
    private String sMSDate;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getSlNo() {
        return slNo;
    }

    public void setSlNo(String slNo) {
        this.slNo = slNo;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getSMSDate() {
        return sMSDate;
    }

    public void setSMSDate(String sMSDate) {
        this.sMSDate = sMSDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
