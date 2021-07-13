package com.project.integratedservices.repository.integratedServicesForAllTypes.response.circular_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CircularResponse {
    @SerializedName("Circular_Name")
    @Expose
    private String circularName;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("File_Name")
    @Expose
    private String fileName;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getCircularName() {
        return circularName;
    }

    public void setCircularName(String circularName) {
        this.circularName = circularName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
