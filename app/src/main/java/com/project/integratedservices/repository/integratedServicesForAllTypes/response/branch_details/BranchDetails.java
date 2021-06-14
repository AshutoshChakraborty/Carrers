package com.project.integratedservices.repository.integratedServicesForAllTypes.response.branch_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BranchDetails {
    @SerializedName("BranchId")
    @Expose
    private String branchId;
    @SerializedName("BranchName")
    @Expose
    private String branchName;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
