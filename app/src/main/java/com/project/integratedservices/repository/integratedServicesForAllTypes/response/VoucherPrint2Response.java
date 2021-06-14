package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VoucherPrint2Response  implements Serializable
{

    @SerializedName("CID")
    @Expose
    private Integer cID;
    @SerializedName("BRANCH")
    @Expose
    private String bRANCH;
    @SerializedName("CD_APL")
    @Expose
    private String cDAPL;
    @SerializedName("SDATE")
    @Expose
    private String sDATE;
    @SerializedName("SCHEME")
    @Expose
    private String sCHEME;
    @SerializedName("COLLECTION")
    @Expose
    private String cOLLECTION;
    @SerializedName("PERCENTAGE")
    @Expose
    private String pERCENTAGE;
    @SerializedName("COMM")
    @Expose
    private String cOMM;
    @SerializedName("SPOT")
    @Expose
    private String sPOT;
    @SerializedName("ADJUST")
    @Expose
    private String aDJUST;
    @SerializedName("R_TYPE")
    @Expose
    private String rTYPE;
    @SerializedName("INVESTOR_JUNIOR")
    @Expose
    private String iNVESTORJUNIOR;
    @SerializedName("Status1")
    @Expose
    private String status1;
    private final static long serialVersionUID = -6423723707001146256L;

    public Integer getCID() {
        return cID;
    }

    public void setCID(Integer cID) {
        this.cID = cID;
    }

    public String getBRANCH() {
        return bRANCH;
    }

    public void setBRANCH(String bRANCH) {
        this.bRANCH = bRANCH;
    }

    public String getCDAPL() {
        return cDAPL;
    }

    public void setCDAPL(String cDAPL) {
        this.cDAPL = cDAPL;
    }

    public String getSDATE() {
        return sDATE;
    }

    public void setSDATE(String sDATE) {
        this.sDATE = sDATE;
    }

    public String getSCHEME() {
        return sCHEME;
    }

    public void setSCHEME(String sCHEME) {
        this.sCHEME = sCHEME;
    }

    public String getCOLLECTION() {
        return cOLLECTION;
    }

    public void setCOLLECTION(String cOLLECTION) {
        this.cOLLECTION = cOLLECTION;
    }

    public String getPERCENTAGE() {
        return pERCENTAGE;
    }

    public void setPERCENTAGE(String pERCENTAGE) {
        this.pERCENTAGE = pERCENTAGE;
    }

    public String getCOMM() {
        return cOMM;
    }

    public void setCOMM(String cOMM) {
        this.cOMM = cOMM;
    }

    public String getSPOT() {
        return sPOT;
    }

    public void setSPOT(String sPOT) {
        this.sPOT = sPOT;
    }

    public String getADJUST() {
        return aDJUST;
    }

    public void setADJUST(String aDJUST) {
        this.aDJUST = aDJUST;
    }

    public String getRTYPE() {
        return rTYPE;
    }

    public void setRTYPE(String rTYPE) {
        this.rTYPE = rTYPE;
    }

    public String getINVESTORJUNIOR() {
        return iNVESTORJUNIOR;
    }

    public void setINVESTORJUNIOR(String iNVESTORJUNIOR) {
        this.iNVESTORJUNIOR = iNVESTORJUNIOR;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

}
