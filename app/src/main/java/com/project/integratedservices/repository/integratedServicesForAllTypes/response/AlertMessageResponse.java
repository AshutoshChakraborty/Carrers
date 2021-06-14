package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlertMessageResponse {
    @SerializedName("SMSTextDisplay")
    @Expose
    private String sMSTextDisplay;
    @SerializedName("LeaveStatus")
    @Expose
    private String leaveStatus;

    public String getSMSTextDisplay() {
        return sMSTextDisplay;
    }

    public void setSMSTextDisplay(String sMSTextDisplay) {
        this.sMSTextDisplay = sMSTextDisplay;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }
}
