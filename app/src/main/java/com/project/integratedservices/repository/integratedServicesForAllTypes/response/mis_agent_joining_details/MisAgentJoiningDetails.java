package com.project.integratedservices.repository.integratedServicesForAllTypes.response.mis_agent_joining_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MisAgentJoiningDetails {
    @SerializedName("Agent")
    @Expose
    private String agent;
    @SerializedName("DOJ")
    @Expose
    private String doj;
    @SerializedName("Introducer")
    @Expose
    private String introducer;
    @SerializedName("branchName")
    @Expose
    private String branchName;
    @SerializedName("RankId")
    @Expose
    private String rankId;
    @SerializedName("GradeName")
    @Expose
    private String gradeName;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getRankId() {
        return rankId;
    }

    public void setRankId(String rankId) {
        this.rankId = rankId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
