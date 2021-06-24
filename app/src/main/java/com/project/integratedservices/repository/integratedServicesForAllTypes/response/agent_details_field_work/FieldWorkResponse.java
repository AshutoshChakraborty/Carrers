package com.project.integratedservices.repository.integratedServicesForAllTypes.response.agent_details_field_work;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FieldWorkResponse {
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("AG_RANK_ID")
    @Expose
    private String agRankId;
    @SerializedName("GradeName")
    @Expose
    private String gradeName;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("DOJ")
    @Expose
    private String doj;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("Pin")
    @Expose
    private String pin;
    @SerializedName("Branch_Name")
    @Expose
    private String branchName;
    @SerializedName("Mobile_No")
    @Expose
    private String mobileNo;
    @SerializedName("PAN")
    @Expose
    private String pan;
    @SerializedName("INTRO_CODE")
    @Expose
    private String introCode;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgRankId() {
        return agRankId;
    }

    public void setAgRankId(String agRankId) {
        this.agRankId = agRankId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getIntroCode() {
        return introCode;
    }

    public void setIntroCode(String introCode) {
        this.introCode = introCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
