package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AgentCommissionDetailsResponse {
    @SerializedName("AG_CODE")
    @Expose
    private String aGCODE;
    @SerializedName("AG_NAME")
    @Expose
    private String aGNAME;
    @SerializedName("DESCRIPTION")
    @Expose
    private String dESCRIPTION;
    @SerializedName("FRMNO")
    @Expose
    private String fRMNO;
    @SerializedName("FRMAMT")
    @Expose
    private String fRMAMT;
    @SerializedName("FRMCOMM")
    @Expose
    private String fRMCOMM;
    @SerializedName("INVESTOR")
    @Expose
    private String iNVESTOR;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getAGCODE() {
        return aGCODE;
    }

    public void setAGCODE(String aGCODE) {
        this.aGCODE = aGCODE;
    }

    public String getAGNAME() {
        return aGNAME;
    }

    public void setAGNAME(String aGNAME) {
        this.aGNAME = aGNAME;
    }

    public String getDESCRIPTION() {
        return dESCRIPTION;
    }

    public void setDESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public String getFRMNO() {
        return fRMNO;
    }

    public void setFRMNO(String fRMNO) {
        this.fRMNO = fRMNO;
    }

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

    public String getINVESTOR() {
        return iNVESTOR;
    }

    public void setINVESTOR(String iNVESTOR) {
        this.iNVESTOR = iNVESTOR;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
