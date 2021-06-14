package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VoucherPrint3Response  implements Serializable
{

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("GROSS")
    @Expose
    private String gROSS;
    @SerializedName("SPOT1")
    @Expose
    private String sPOT1;
    @SerializedName("TDS_HELDUP")
    @Expose
    private String tDSHELDUP;
    @SerializedName("TDS_DEDUCT")
    @Expose
    private String tDSDEDUCT;
    @SerializedName("TDS_NOPAN")
    @Expose
    private String tDSNOPAN;
    @SerializedName("TDS_CLEARING")
    @Expose
    private String tDSCLEARING;
    @SerializedName("NEG_BALANCE")
    @Expose
    private String nEGBALANCE;
    @SerializedName("FINAL_COMM")
    @Expose
    private String fINALCOMM;
    @SerializedName("Status2")
    @Expose
    private String status2;
    private final static long serialVersionUID = 5343717590888152083L;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getGROSS() {
        return gROSS;
    }

    public void setGROSS(String gROSS) {
        this.gROSS = gROSS;
    }

    public String getSPOT1() {
        return sPOT1;
    }

    public void setSPOT1(String sPOT1) {
        this.sPOT1 = sPOT1;
    }

    public String getTDSHELDUP() {
        return tDSHELDUP;
    }

    public void setTDSHELDUP(String tDSHELDUP) {
        this.tDSHELDUP = tDSHELDUP;
    }

    public String getTDSDEDUCT() {
        return tDSDEDUCT;
    }

    public void setTDSDEDUCT(String tDSDEDUCT) {
        this.tDSDEDUCT = tDSDEDUCT;
    }

    public String getTDSNOPAN() {
        return tDSNOPAN;
    }

    public void setTDSNOPAN(String tDSNOPAN) {
        this.tDSNOPAN = tDSNOPAN;
    }

    public String getTDSCLEARING() {
        return tDSCLEARING;
    }

    public void setTDSCLEARING(String tDSCLEARING) {
        this.tDSCLEARING = tDSCLEARING;
    }

    public String getNEGBALANCE() {
        return nEGBALANCE;
    }

    public void setNEGBALANCE(String nEGBALANCE) {
        this.nEGBALANCE = nEGBALANCE;
    }

    public String getFINALCOMM() {
        return fINALCOMM;
    }

    public void setFINALCOMM(String fINALCOMM) {
        this.fINALCOMM = fINALCOMM;
    }

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }

}
