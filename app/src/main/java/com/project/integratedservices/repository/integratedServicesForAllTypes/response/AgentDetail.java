package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AgentDetail {
    @SerializedName("Slno")
    @Expose
    private Integer slno;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Introducer")
    @Expose
    private String introducer;
    @SerializedName("DOJ")
    @Expose
    private String doj;
    @SerializedName("Enrol_Amt")
    @Expose
    private String enrolAmt;
    @SerializedName("RankId")
    @Expose
    private String rankId;
    @SerializedName("GradeName")
    @Expose
    private String gradeName;
    @SerializedName("Status")
    @Expose
    private String status;

    public Integer getSlno() {
        return slno;
    }

    public void setSlno(Integer slno) {
        this.slno = slno;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getEnrolAmt() {
        return enrolAmt;
    }

    public void setEnrolAmt(String enrolAmt) {
        this.enrolAmt = enrolAmt;
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
