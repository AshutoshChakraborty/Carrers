package com.project.integratedservices.repository.integratedServicesForAllTypes.response.agent_details_payment_detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentDetail {
    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("DESCRIPTION")
    @Expose
    private String description;
    @SerializedName("STATEMENT_DATE")
    @Expose
    private String statementDate;
    @SerializedName("GROSS_COMM")
    @Expose
    private String grossComm;
    @SerializedName("SPORT_COMM")
    @Expose
    private String sportComm;
    @SerializedName("HPTDS")
    @Expose
    private String hptds;
    @SerializedName("TDS")
    @Expose
    private String tds;
    @SerializedName("TDS_NO_PAN")
    @Expose
    private String tdsNoPan;
    @SerializedName("FWDF")
    @Expose
    private String fwdf;
    @SerializedName("NET_COMM")
    @Expose
    private String netComm;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(String statementDate) {
        this.statementDate = statementDate;
    }

    public String getGrossComm() {
        return grossComm;
    }

    public void setGrossComm(String grossComm) {
        this.grossComm = grossComm;
    }

    public String getSportComm() {
        return sportComm;
    }

    public void setSportComm(String sportComm) {
        this.sportComm = sportComm;
    }

    public String getHptds() {
        return hptds;
    }

    public void setHptds(String hptds) {
        this.hptds = hptds;
    }

    public String getTds() {
        return tds;
    }

    public void setTds(String tds) {
        this.tds = tds;
    }

    public String getTdsNoPan() {
        return tdsNoPan;
    }

    public void setTdsNoPan(String tdsNoPan) {
        this.tdsNoPan = tdsNoPan;
    }

    public String getFwdf() {
        return fwdf;
    }

    public void setFwdf(String fwdf) {
        this.fwdf = fwdf;
    }

    public String getNetComm() {
        return netComm;
    }

    public void setNetComm(String netComm) {
        this.netComm = netComm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
