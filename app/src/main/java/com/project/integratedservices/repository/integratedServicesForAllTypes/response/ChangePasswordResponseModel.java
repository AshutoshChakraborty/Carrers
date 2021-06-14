package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordResponseModel {
    @SerializedName("Userid")
    @Expose
    private Object userid;
    @SerializedName("OldPassword")
    @Expose
    private Object oldPassword;
    @SerializedName("NewPassword")
    @Expose
    private Object newPassword;
    @SerializedName("Status")
    @Expose
    private String status;

    public Object getUserid() {
        return userid;
    }

    public void setUserid(Object userid) {
        this.userid = userid;
    }

    public Object getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(Object oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Object getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(Object newPassword) {
        this.newPassword = newPassword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}