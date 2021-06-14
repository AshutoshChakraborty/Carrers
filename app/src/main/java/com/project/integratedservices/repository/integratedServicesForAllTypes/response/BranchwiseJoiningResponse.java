package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BranchwiseJoiningResponse implements Serializable {
    @SerializedName("AgentCode")
    @Expose
    private String agentCode;
    @SerializedName("AgentType")
    @Expose
    private String agentType;
    @SerializedName("AgentName")
    @Expose
    private String agentName;
    @SerializedName("JoiningDate")
    @Expose
    private String JoiningDate;
    @SerializedName("NO_OF_JOINING")
    @Expose
    private String NO_OF_JOINING;
   /* @SerializedName("Rank")
    @Expose
    private String rank;*/
    @SerializedName("IntroducerCode")
    @Expose
    private String introducerCode;
    /*@SerializedName("BranchName")
    @Expose
    private String branchName;*/
    @SerializedName("Status")
    @Expose
    private String status;

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }



    public String getIntroducerCode() {
        return introducerCode;
    }

    public void setIntroducerCode(String introducerCode) {
        this.introducerCode = introducerCode;
    }

    public String getJoiningDate() {
        return JoiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        JoiningDate = joiningDate;
    }

    public String getNO_OF_JOINING() {
        return NO_OF_JOINING;
    }

    public void setNO_OF_JOINING(String NO_OF_JOINING) {
        this.NO_OF_JOINING = NO_OF_JOINING;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
