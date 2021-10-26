package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MISCollectionRegisterResponse implements Serializable
{

    @SerializedName("RANK")
    @Expose
    private String rank;
    @SerializedName("GRADE")
    @Expose
    private String grade;
    @SerializedName("Ref_No")
    @Expose
    private String refNo;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Branch")
    @Expose
    private String branch;
    @SerializedName("DOJ")
    @Expose
    private String doj;
    @SerializedName("Introducer")
    @Expose
    private String introducer;
    @SerializedName("Premium_Frequency")
    @Expose
    private String premiumFrequency;
    @SerializedName("Premium_Amount")
    @Expose
    private String premiumAmount;
    @SerializedName("Fresh_Weighted")
    @Expose
    private String freshWeighted;
    @SerializedName("Renewal_Weighted")
    @Expose
    private String renewalWeighted;
    @SerializedName("Team_Collection_Fresh")
    @Expose
    private String teamCollectionFresh;
    @SerializedName("Team_Collection_Renewal")
    @Expose
    private String teamCollectionRenewal;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getPremiumFrequency() {
        return premiumFrequency;
    }

    public void setPremiumFrequency(String premiumFrequency) {
        this.premiumFrequency = premiumFrequency;
    }

    public String getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(String premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public String getFreshWeighted() {
        return freshWeighted;
    }

    public void setFreshWeighted(String freshWeighted) {
        this.freshWeighted = freshWeighted;
    }

    public String getRenewalWeighted() {
        return renewalWeighted;
    }

    public void setRenewalWeighted(String renewalWeighted) {
        this.renewalWeighted = renewalWeighted;
    }

    public String getTeamCollectionFresh() {
        return teamCollectionFresh;
    }

    public void setTeamCollectionFresh(String teamCollectionFresh) {
        this.teamCollectionFresh = teamCollectionFresh;
    }

    public String getTeamCollectionRenewal() {
        return teamCollectionRenewal;
    }

    public void setTeamCollectionRenewal(String teamCollectionRenewal) {
        this.teamCollectionRenewal = teamCollectionRenewal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
