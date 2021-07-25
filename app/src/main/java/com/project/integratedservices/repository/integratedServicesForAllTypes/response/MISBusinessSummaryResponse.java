package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MISBusinessSummaryResponse implements Serializable
{

    @SerializedName("Branch_Code")
    @Expose
    private String branchCode;
    @SerializedName("Branch_Name")
    @Expose
    private String branchName;
    @SerializedName("Business_Type")
    @Expose
    private String businessType;
    @SerializedName("Company_Name")
    @Expose
    private String companyName;
    @SerializedName("NoOfPolicy")
    @Expose
    private String noOfPolicy;
    @SerializedName("ActualPremium")
    @Expose
    private String actualPremium;
    @SerializedName("PremiumAmount")
    @Expose
    private String premiumAmt;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("BusinessDate")
    @Expose
    private String BusinessDate;

    @SerializedName("Weightage")
    @Expose
    private String Weightage;

    private final static long serialVersionUID = -1744910674576018680L;

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNoOfPolicy() {
        return noOfPolicy;
    }

    public void setNoOfPolicy(String noOfPolicy) {
        this.noOfPolicy = noOfPolicy;
    }

    public String getActualPremium() {
        return actualPremium;
    }

    public void setActualPremium(String actualPremium) {
        this.actualPremium = actualPremium;
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

    public String getBusinessDate() {
        return BusinessDate;
    }

    public void setBusinessDate(String businessDate) {
        BusinessDate = businessDate;
    }

    public String getWeightage() {
        return Weightage;
    }

    public void setWeightage(String weightage) {
        Weightage = weightage;
    }
}