package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VoucherPrint1Response implements Serializable
{

    @SerializedName("DESCRIPTION")
    @Expose
    private String dESCRIPTION;
    @SerializedName("STMT_DATE")
    @Expose
    private String sTMTDATE;
    @SerializedName("BRANCH1")
    @Expose
    private String bRANCH1;
    @SerializedName("CODE")
    @Expose
    private Integer cODE;
    @SerializedName("RANK")
    @Expose
    private Integer rANK;
    @SerializedName("NAME")
    @Expose
    private String nAME;
    @SerializedName("PAN")
    @Expose
    private String pAN;
    @SerializedName("TOTAL_COLLECTION")
    @Expose
    private String tOTALCOLLECTION;
    @SerializedName("TOTALTILLDATE")
    @Expose
    private String tOTALTILLDATE;
    @SerializedName("LIFE_HELDUP_AMOUNT")
    @Expose
    private String lIFEHELDUPAMOUNT;
    @SerializedName("PanEntryDate")
    @Expose
    private String panEntryDate;
    @SerializedName("CurrentDate")
    @Expose
    private String currrentDate;
    @SerializedName("UpToDate")
    @Expose
    private String uptoDate;
    @SerializedName("ChainDetails")
    @Expose
    private String chainDetails;
    @SerializedName("Status")
    @Expose
    private String status0;
    private final static long serialVersionUID = -7697565700624826239L;

    public String getDESCRIPTION() {
        return dESCRIPTION;
    }

    public void setDESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public String getSTMTDATE() {
        return sTMTDATE;
    }

    public void setSTMTDATE(String sTMTDATE) {
        this.sTMTDATE = sTMTDATE;
    }

    public String getBRANCH1() {
        return bRANCH1;
    }

    public void setBRANCH1(String bRANCH1) {
        this.bRANCH1 = bRANCH1;
    }

    public Integer getCODE() {
        return cODE;
    }

    public void setCODE(Integer cODE) {
        this.cODE = cODE;
    }

    public Integer getRANK() {
        return rANK;
    }

    public void setRANK(Integer rANK) {
        this.rANK = rANK;
    }

    public String getNAME() {
        return nAME;
    }

    public void setNAME(String nAME) {
        this.nAME = nAME;
    }

    public String getPAN() {
        return pAN;
    }

    public void setPAN(String pAN) {
        this.pAN = pAN;
    }

    public String getTOTALCOLLECTION() {
        return tOTALCOLLECTION;
    }

    public void setTOTALCOLLECTION(String tOTALCOLLECTION) {
        this.tOTALCOLLECTION = tOTALCOLLECTION;
    }

    public String getTOTALTILLDATE() {
        return tOTALTILLDATE;
    }

    public void setTOTALTILLDATE(String tOTALTILLDATE) {
        this.tOTALTILLDATE = tOTALTILLDATE;
    }

    public String getLIFEHELDUPAMOUNT() {
        return lIFEHELDUPAMOUNT;
    }

    public void setLIFEHELDUPAMOUNT(String lIFEHELDUPAMOUNT) {
        this.lIFEHELDUPAMOUNT = lIFEHELDUPAMOUNT;
    }

    public String getPanEntryDate() {
        return panEntryDate;
    }

    public void setPanEntryDate(String panEntryDate) {
        this.panEntryDate = panEntryDate;
    }

    public String getCurrrentDate() {
        return currrentDate;
    }

    public void setCurrrentDate(String currrentDate) {
        this.currrentDate = currrentDate;
    }

    public String getChainDetails() {
        return chainDetails;
    }

    public void setChainDetails(String chainDetails) {
        this.chainDetails = chainDetails;
    }

    public String getUptoDate() {
        return uptoDate;
    }

    public void setUptoDate(String uptoDate) {
        this.uptoDate = uptoDate;
    }


    public String getStatus0() {
        return status0;
    }

    public void setStatus0(String status0) {
        this.status0 = status0;
    }

}
