package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MISIndividualBusinessResponse implements Serializable
{

    @SerializedName("Code")
    @Expose
    private Integer code;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Branch_Name")
    @Expose
    private String branchName;
    @SerializedName("Company_Name")
    @Expose
    private String companyName;
    @SerializedName("Business_Date")
    @Expose
    private String businessDate;
    @SerializedName("Appln_No")
    @Expose
    private String applnNo;
    @SerializedName("Business_Type")
    @Expose
    private String businessType;
    @SerializedName("Proposer_Name")
    @Expose
    private String proposerName;
    @SerializedName("Plan_Name")
    @Expose
    private String planName;
    @SerializedName("Premium_Frequency")
    @Expose
    private String premiumFrequency;
    @SerializedName("Installment")
    @Expose
    private Integer installment;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Prem_Term")
    @Expose
    private String premTerm;
    @SerializedName("Premium_Amt")
    @Expose
    private String premiumAmt;
    @SerializedName("Status1")
    @Expose
    private String status1;
    @SerializedName("Weighted_Premium")
    @Expose
    private String weightedPremium;
    @SerializedName("PremiumWithTax")
    @Expose
    private String premiumWithTax;



    private final static long serialVersionUID = -6984537136700175427L;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public String getApplnNo() {
        return applnNo;
    }

    public void setApplnNo(String applnNo) {
        this.applnNo = applnNo;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
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

    public String getPremiumFrequency() {
        return premiumFrequency;
    }

    public void setPremiumFrequency(String premiumFrequency) {
        this.premiumFrequency = premiumFrequency;
    }

    public Integer getInstallment() {
        return installment;
    }

    public void setInstallment(Integer installment) {
        this.installment = installment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPremTerm() {
        return premTerm;
    }

    public void setPremTerm(String premTerm) {
        this.premTerm = premTerm;
    }

    public String getPremiumAmt() {
        return premiumAmt;
    }

    public void setPremiumAmt(String premiumAmt) {
        this.premiumAmt = premiumAmt;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getWeightedPremium() {
        return weightedPremium;
    }

    public void setWeightedPremium(String weightedPremium) {
        this.weightedPremium = weightedPremium;
    }

    public String getPremiumWithTax() {
        return premiumWithTax;
    }

    public void setPremiumWithTax(String premiumWithTax) {
        this.premiumWithTax = premiumWithTax;
    }
}
