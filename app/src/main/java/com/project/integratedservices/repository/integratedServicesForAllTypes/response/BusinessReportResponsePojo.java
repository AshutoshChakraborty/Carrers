package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BusinessReportResponsePojo implements Serializable {
    @SerializedName("AgentCode")
    @Expose
    private String agentCode;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("IntroCode")
    @Expose
    private String introCode;
    @SerializedName("Ag_Rank_Id")
    @Expose
    private String agRankId;
    @SerializedName("TotalBusiness")
    @Expose
    private String totalBusiness;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Fresh")
    @Expose
    private String fresh;
    @SerializedName("Renewal")
    @Expose
    private String renewal;
    private final static long serialVersionUID = -1165563172303869207L;

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroCode() {
        return introCode;
    }

    public void setIntroCode(String introCode) {
        this.introCode = introCode;
    }

    public String getAgRankId() {
        return agRankId;
    }

    public void setAgRankId(String agRankId) {
        this.agRankId = agRankId;
    }

    public String getTotalBusiness() {
        return totalBusiness;
    }

    public void setTotalBusiness(String totalBusiness) {
        this.totalBusiness = totalBusiness;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRenewal() {
        return renewal;
    }

    public void setRenewal(String renewal) {
        this.renewal = renewal;
    }

    public String getFresh() {
        return fresh;
    }

    public void setFresh(String fresh) {
        this.fresh = fresh;
    }
}
