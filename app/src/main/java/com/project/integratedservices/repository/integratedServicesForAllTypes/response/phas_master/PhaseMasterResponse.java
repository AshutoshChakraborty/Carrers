package com.project.integratedservices.repository.integratedServicesForAllTypes.response.phas_master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhaseMasterResponse {
    @SerializedName("PhaseId")
    @Expose
    private Integer phaseId;
    @SerializedName("PhaseName")
    @Expose
    private String phaseName;
    @SerializedName("Status")
    @Expose
    private String status;

    public Integer getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Integer phaseId) {
        this.phaseId = phaseId;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
