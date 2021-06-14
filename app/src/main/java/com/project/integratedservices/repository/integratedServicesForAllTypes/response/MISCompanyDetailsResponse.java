package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MISCompanyDetailsResponse implements Serializable
{

    @SerializedName("CompanyName")
    @Expose
    private String companyName;
    @SerializedName("Status")
    @Expose
    private String status;
    private final static long serialVersionUID = 3849552309395651006L;

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

}
