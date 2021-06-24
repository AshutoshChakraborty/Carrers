package com.project.integratedservices.repository.integratedServicesForAllTypes.response.agent_details_bank_detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankDetailResponse {

    @SerializedName("BANK_NAME")
    @Expose
    private String bankName;
    @SerializedName("BRANCH")
    @Expose
    private String branch;
    @SerializedName("IFSC")
    @Expose
    private String ifsc;
    @SerializedName("ACCNT_NO")
    @Expose
    private String accntNo;
    @SerializedName("MICR")
    @Expose
    private String micr;
    @SerializedName("VERIFIED")
    @Expose
    private String verified;
    @SerializedName("VERIFIED_DATE")
    @Expose
    private String verifiedDate;
    @SerializedName("ACTIVE")
    @Expose
    private String active;
    @SerializedName("REMARKS")
    @Expose
    private String remarks;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getAccntNo() {
        return accntNo;
    }

    public void setAccntNo(String accntNo) {
        this.accntNo = accntNo;
    }

    public String getMicr() {
        return micr;
    }

    public void setMicr(String micr) {
        this.micr = micr;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(String verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
