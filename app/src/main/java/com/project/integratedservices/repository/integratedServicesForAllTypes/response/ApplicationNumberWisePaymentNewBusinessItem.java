package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplicationNumberWisePaymentNewBusinessItem {


    @SerializedName("Date")
    @Expose
    private String date;

    @SerializedName("FRMREG")
    @Expose
    private String FRMREG;

    @SerializedName("RefNo")
    @Expose
    private String RefNo;

    @SerializedName("Proposer_Name")
    @Expose
    private String Proposer_Name;

    @SerializedName("Plan_Name")
    @Expose
    private String Plan_Name;

    @SerializedName("Business_Type")
    @Expose
    private String Business_Type;

    @SerializedName("PremiumWithTax")
    @Expose
    private String PremiumWithTax;

    @SerializedName("Premium_Amt")
    @Expose
    private String Premium_Amt;

    @SerializedName("Weightage")
    @Expose
    private String Weightage;

    @SerializedName("Status_")
    @Expose
    private String Status_;

    @SerializedName("Premium_Frequency")
    @Expose
    private String Premium_Frequency;

    @SerializedName("Installment")
    @Expose
    private String Installment;

    @SerializedName("Status")
    @Expose
    private String Status;


    public String getRefNo() {
        return RefNo;
    }

    public void setRefNo(String refNo) {
        this.RefNo = RefNo;
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

    public String getPlan_Name() {
        return Plan_Name;
    }

    public void setPlan_Name(String Plan_Name) {
        this.Plan_Name = Plan_Name;
    }

    public String getBusiness_Type() {
        return Business_Type;
    }

    public void setBusiness_Type(String Business_Type) {
        this.Business_Type = Business_Type;
    }

    public String getPremiumWithTax() {
        return PremiumWithTax;
    }

    public void setPremiumWithTax(String PremiumWithTax) {
        this.PremiumWithTax = PremiumWithTax;
    }

    public String getPremium_Amt() {
        return Premium_Amt;
    }

    public void setPremium_Amt(String Premium_Amt) {
        this.Premium_Amt = Premium_Amt;
    }

    public String getWeightage() {
        return Weightage;
    }

    public void setWeightage(String Weightage) {
        this.Weightage = Weightage;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getPremium_Frequency() {
        return Premium_Frequency;
    }

    public void setPremium_Frequency(String Premium_Frequency) {
        this.Premium_Frequency = Premium_Frequency;
    }
}
