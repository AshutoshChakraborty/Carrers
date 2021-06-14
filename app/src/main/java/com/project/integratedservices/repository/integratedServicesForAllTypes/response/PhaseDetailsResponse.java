package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PhaseDetailsResponse implements Serializable
{

    @SerializedName("phaseName")
    @Expose
    private String phaseName;
    @SerializedName("phaseId")
    @Expose
    private String phaseId;
    @SerializedName("Status")
    @Expose
    private String status;
    private final static long serialVersionUID = -7443808327923587712L;

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public String getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(String phaseId) {
        this.phaseId = phaseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
