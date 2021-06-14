package com.project.integratedservices.repository.integratedServicesForAllTypes.response.intro_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IntroDetails {
    @SerializedName("AgentCode")
    @Expose
    private String agentCode;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Grade")
    @Expose
    private String grade;
    @SerializedName("CompanyId")
    @Expose
    private String companyId;
    @SerializedName("CompanyName")
    @Expose
    private String companyName;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("GradeId")
    @Expose
    private String GradeId;

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGradeId() {
        return GradeId;
    }

    public void setGradeId(String gradeId) {
        GradeId = gradeId;
    }
}
