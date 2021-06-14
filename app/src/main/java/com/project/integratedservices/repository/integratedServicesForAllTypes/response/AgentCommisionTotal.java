package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AgentCommisionTotal {
    @SerializedName("FRMAMT")
    @Expose
    private String fRMAMT;
    @SerializedName("FRMCOMM")
    @Expose
    private String fRMCOMM;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getFRMAMT() {
        return fRMAMT;
    }

    public void setFRMAMT(String fRMAMT) {
        this.fRMAMT = fRMAMT;
    }

    public String getFRMCOMM() {
        return fRMCOMM;
    }

    public void setFRMCOMM(String fRMCOMM) {
        this.fRMCOMM = fRMCOMM;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
