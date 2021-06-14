package com.project.integratedservices.repository.integratedServicesForAllTypes.response.grade_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GradeDetails {
    @SerializedName("GradeId")
    @Expose
    private String gradeId;
    @SerializedName("GradeName")
    @Expose
    private String gradeName;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
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
