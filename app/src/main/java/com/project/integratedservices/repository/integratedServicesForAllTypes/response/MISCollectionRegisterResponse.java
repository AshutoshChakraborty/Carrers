package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MISCollectionRegisterResponse implements Serializable
{

    @SerializedName("AgentCode")
    @Expose
    private Integer agentCode;

    @SerializedName("AgentName")
    @Expose
    private String name;

    @SerializedName("TotalCollection")
    @Expose
    private String totalCollection;

    @SerializedName("Total_Joining")
    @Expose
    private String totalJoining;

    @SerializedName("Status")
    @Expose
    private String status;

    private final static long serialVersionUID = -5128830576690959174L;

    public Integer getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(Integer agentCode) {
        this.agentCode = agentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalCollection() {
        return totalCollection;
    }

    public void setTotalCollection(String totalCollection) {
        this.totalCollection = totalCollection;
    }

    public String getTotalJoining() {
        return totalJoining;
    }

    public void setTotalJoining(String totalJoining) {
        this.totalJoining = totalJoining;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
