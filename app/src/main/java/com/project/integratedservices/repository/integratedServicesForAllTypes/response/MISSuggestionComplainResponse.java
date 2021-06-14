package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MISSuggestionComplainResponse  implements Serializable
{

    @SerializedName("Status")
    @Expose
    private String status;
    private final static long serialVersionUID = 7350985510620185807L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}