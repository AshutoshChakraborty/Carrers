package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MISBusinessSummaryResponse implements Serializable
{

    @SerializedName("Branch_Code")
    @Expose
    private Integer branchCode;
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
    private Integer noOfPolicy;
    @SerializedName("ActualPremium")
    @Expose
    private Integer actualPremium;
    @SerializedName("Premium_Amt")
    @Expose
    private Integer premiumAmt;
    @SerializedName("Status")
    @Expose
    private String status;
    private final static long serialVersionUID = -1744910674576018680L;

    public Integer getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(Integer branchCode) {
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

    public Integer getNoOfPolicy() {
        return noOfPolicy;
    }

    public void setNoOfPolicy(Integer noOfPolicy) {
        this.noOfPolicy = noOfPolicy;
    }

    public Integer getActualPremium() {
        return actualPremium;
    }

    public void setActualPremium(Integer actualPremium) {
        this.actualPremium = actualPremium;
    }

    public Integer getPremiumAmt() {
        return premiumAmt;
    }

    public void setPremiumAmt(Integer premiumAmt) {
        this.premiumAmt = premiumAmt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}