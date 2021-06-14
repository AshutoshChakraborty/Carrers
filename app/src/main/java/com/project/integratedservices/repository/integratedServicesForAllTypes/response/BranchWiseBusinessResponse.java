package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BranchWiseBusinessResponse implements Serializable
{

    @SerializedName("Branch")
    @Expose
    private String branch;
    @SerializedName("Company_Name")
    @Expose
    private String companyName;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Agent")
    @Expose
    private String agent;
    @SerializedName("Appln_No")
    @Expose
    private String applnNo;
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
    @SerializedName("Policy_Term")
    @Expose
    private Integer policyTerm;
    @SerializedName("Prem_Term")
    @Expose
    private Integer premTerm;
    @SerializedName("SumAssured")
    @Expose
    private Double sumAssured;
    @SerializedName("Actual_Premium")
    @Expose
    private Double actualPremium;
    @SerializedName("Weighted_Premium_Amt")
    @Expose
    private Double weightedPremiumAmt;
    @SerializedName("Status")
    @Expose
    private String status;
    private final static long serialVersionUID = 3470253125889901194L;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getApplnNo() {
        return applnNo;
    }

    public void setApplnNo(String applnNo) {
        this.applnNo = applnNo;
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

    public Integer getPolicyTerm() {
        return policyTerm;
    }

    public void setPolicyTerm(Integer policyTerm) {
        this.policyTerm = policyTerm;
    }

    public Integer getPremTerm() {
        return premTerm;
    }

    public void setPremTerm(Integer premTerm) {
        this.premTerm = premTerm;
    }

    public Double getSumAssured() {
        return sumAssured;
    }

    public void setSumAssured(Double sumAssured) {
        this.sumAssured = sumAssured;
    }

    public Double getActualPremium() {
        return actualPremium;
    }

    public void setActualPremium(Double actualPremium) {
        this.actualPremium = actualPremium;
    }

    public Double getWeightedPremiumAmt() {
        return weightedPremiumAmt;
    }

    public void setWeightedPremiumAmt(Double weightedPremiumAmt) {
        this.weightedPremiumAmt = weightedPremiumAmt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
