package com.project.integratedservices.repository.authencationRepo.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequestModel {
    @SerializedName("AgentCode")
    @Expose
    private String agentCode;
    @SerializedName("Userid")
    @Expose
    private String userid;
    @SerializedName("Passowrd")
    @Expose
    private String passowrd;
    @SerializedName("imei_code")
    @Expose
    private String imeiCode;
    @SerializedName("tokenid")
    @Expose
    private String tokenid;


    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        if(passowrd.equals(null))
            passowrd ="";

        this.passowrd = passowrd;
    }

    public String getImeiCode() {
        return imeiCode;
    }

    public void setImeiCode(String imeiCode) {
        this.imeiCode = imeiCode;
    }

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }
}
