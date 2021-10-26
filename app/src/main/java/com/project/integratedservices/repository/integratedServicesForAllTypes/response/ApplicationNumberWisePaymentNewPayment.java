package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplicationNumberWisePaymentNewPayment {


    @SerializedName("Business_Date")
    @Expose
    private String date;

    @SerializedName("Application_No")
    @Expose
    private String applicationNumber;

    @SerializedName("FRMREG")
    @Expose
    private String FRMREG;

    @SerializedName("RefNo")
    @Expose
    private String RefNo;

    @SerializedName("Investor")
    @Expose
    private String Proposer_Name;

    @SerializedName("FrmComm")
    @Expose
    private String FrmComm;

    @SerializedName("FrmAmt")
    @Expose
    private String FrmAmt;

    @SerializedName("Status")
    @Expose
    private String Status;

    @SerializedName("Description")
    @Expose
    private String description;


    public String getRefNo() {
        return RefNo;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }



    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public void setRefNo(String refNo) {
        this.RefNo = RefNo;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String refNo) {
        this.description = RefNo;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFRMREG() {
        return FRMREG;
    }

    public void setFRMREG(String FRMREG) {
        this.FRMREG = FRMREG;
    }

    public String getProposer_Name() {
        return Proposer_Name;
    }

    public void setProposer_Name(String Proposer_Name) {
        this.Proposer_Name = Proposer_Name;
    }

    public String getFrmComm() {
        return FrmComm;
    }

    public void setFrmComm(String FrmComm) {
        this.FrmComm = FrmComm;
    }

    public String getFrmAmt() {
        return FrmAmt;
    }

    public void setFrmAmt(String FrmAmt) {
        this.FrmAmt = FrmAmt;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

}
