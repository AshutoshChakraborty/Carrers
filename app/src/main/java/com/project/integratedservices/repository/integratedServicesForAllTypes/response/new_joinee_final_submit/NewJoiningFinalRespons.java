package com.project.integratedservices.repository.integratedServicesForAllTypes.response.new_joinee_final_submit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewJoiningFinalRespons {
    @SerializedName("Number")
    @Expose
    private String number;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("IntroNumber")
    @Expose
    private String introNumber;
    @SerializedName("Intro_Name")
    @Expose
    private String introName;
    @SerializedName("CommencementFrom")
    @Expose
    private String commencementFrom;
    @SerializedName("EnrollmentAmount")
    @Expose
    private String enrollmentAmount;
    @SerializedName("Rank")
    @Expose
    private String rank;
    @SerializedName("IntroRank")
    @Expose
    private String introRank;
    @SerializedName("RDate")
    @Expose
    private String rDate;
    @SerializedName("PhaseID")
    @Expose
    private String phaseID;
    @SerializedName("Unit")
    @Expose
    private String unit;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("PhoneNo")
    @Expose
    private Object phoneNo;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroNumber() {
        return introNumber;
    }

    public void setIntroNumber(String introNumber) {
        this.introNumber = introNumber;
    }

    public String getIntroName() {
        return introName;
    }

    public void setIntroName(String introName) {
        this.introName = introName;
    }

    public String getCommencementFrom() {
        return commencementFrom;
    }

    public void setCommencementFrom(String commencementFrom) {
        this.commencementFrom = commencementFrom;
    }

    public String getEnrollmentAmount() {
        return enrollmentAmount;
    }

    public void setEnrollmentAmount(String enrollmentAmount) {
        this.enrollmentAmount = enrollmentAmount;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getIntroRank() {
        return introRank;
    }

    public void setIntroRank(String introRank) {
        this.introRank = introRank;
    }

    public String getRDate() {
        return rDate;
    }

    public void setRDate(String rDate) {
        this.rDate = rDate;
    }

    public String getPhaseID() {
        return phaseID;
    }

    public void setPhaseID(String phaseID) {
        this.phaseID = phaseID;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Object getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Object phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
