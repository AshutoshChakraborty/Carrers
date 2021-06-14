package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplicationNumberWisePaymentResponse {
    @SerializedName("Company_Name")
    @Expose
    private String companyName;
    @SerializedName("Branch_Name")
    @Expose
    private String branchName;
    @SerializedName("Branch_Code")
    @Expose
    private String branchCode;
    @SerializedName("Business_Date")
    @Expose
    private String businessDate;
    @SerializedName("Proposer_Name")
    @Expose
    private String proposerName;
    @SerializedName("Plan_Name")
    @Expose
    private String planName;
    @SerializedName("Business_Type")
    @Expose
    private String businessType;
    @SerializedName("Premium_Frequency")
    @Expose
    private String premiumFrequency;
    @SerializedName("Sum_Assured")
    @Expose
    private String sumAssured;
    @SerializedName("Premium_Amt")
    @Expose
    private String premiumAmt;
    @SerializedName("Status")
    @Expose
    private String status;

    @SerializedName("VoucherNo")
    @Expose
    private String voucherno;
    @SerializedName("Date")
    @Expose
    private String date;

    @SerializedName("Application_No")
    @Expose
    private String application_no;
    @SerializedName("FrmAmt")
    @Expose
    private String frmamt;
    @SerializedName("FrmComm")
    @Expose
    private String frmcomm;
    @SerializedName("Investor")
    @Expose
    private String investor;

    @SerializedName("AgentCode")
    @Expose
    private String agentcode;


    public String getAgentcode() {
        return agentcode;
    }

    public void setAgentcode(String agentcode) {
        this.agentcode = agentcode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public String getProposerName() {
        return proposerName;
    }

    public void setProposerName(String proposerName) {
        this.proposerName = proposerName;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getPremiumFrequency() {
        return premiumFrequency;
    }

    public void setPremiumFrequency(String premiumFrequency) {
        this.premiumFrequency = premiumFrequency;
    }

    public String getSumAssured() {
        return sumAssured;
    }

    public void setSumAssured(String sumAssured) {
        this.sumAssured = sumAssured;
    }

    public String getPremiumAmt() {
        return premiumAmt;
    }

    public void setPremiumAmt(String premiumAmt) {
        this.premiumAmt = premiumAmt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getVoucherno() {
        return voucherno;
    }

    public void setVoucherno(String voucherno) {
        this.voucherno = voucherno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getApplication_no() {
        return application_no;
    }

    public void setApplication_no(String application_no) {
        this.application_no = application_no;
    }

    public String getFrmamt() {
        return frmamt;
    }

    public void setFrmamt(String frmamt) {
        this.frmamt = frmamt;
    }

    public String getFrmcomm() {
        return frmcomm;
    }

    public void setFrmcomm(String frmcomm) {
        this.frmcomm = frmcomm;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }
}
