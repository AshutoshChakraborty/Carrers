
package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VoucherPrint5Response {

    @SerializedName("SUR")
    @Expose
    private Integer sur;
    @SerializedName("TMA_I")
    @Expose
    private Integer tmaI;
    @SerializedName("TMA_II")
    @Expose
    private Integer tmaIi;
    @SerializedName("JMA")
    @Expose
    private Integer jma;
    @SerializedName("DMA")
    @Expose
    private Integer dma;
    @SerializedName("MA")
    @Expose
    private Integer ma;
    @SerializedName("SMA")
    @Expose
    private Integer sma;
    @SerializedName("AMO")
    @Expose
    private Integer amo;
    @SerializedName("MO")
    @Expose
    private Integer mo;
    @SerializedName("SMO")
    @Expose
    private Integer smo;
    @SerializedName("PMO")
    @Expose
    private Integer pmo;
    @SerializedName("JRM")
    @Expose
    private Integer jrm;
    @SerializedName("ARM")
    @Expose
    private Integer arm;
    @SerializedName("RM")
    @Expose
    private Integer rm;
    @SerializedName("SRM")
    @Expose
    private Integer srm;
    @SerializedName("CRM")
    @Expose
    private Integer crm;
    @SerializedName("Status")
    @Expose
    private String status;

    public Integer getSur() {
        return sur;
    }

    public void setSur(Integer sur) {
        this.sur = sur;
    }

    public Integer getTmaI() {
        return tmaI;
    }

    public void setTmaI(Integer tmaI) {
        this.tmaI = tmaI;
    }

    public Integer getTmaIi() {
        return tmaIi;
    }

    public void setTmaIi(Integer tmaIi) {
        this.tmaIi = tmaIi;
    }

    public Integer getJma() {
        return jma;
    }

    public void setJma(Integer jma) {
        this.jma = jma;
    }

    public Integer getDma() {
        return dma;
    }

    public void setDma(Integer dma) {
        this.dma = dma;
    }

    public Integer getMa() {
        return ma;
    }

    public void setMa(Integer ma) {
        this.ma = ma;
    }

    public Integer getSma() {
        return sma;
    }

    public void setSma(Integer sma) {
        this.sma = sma;
    }

    public Integer getAmo() {
        return amo;
    }

    public void setAmo(Integer amo) {
        this.amo = amo;
    }

    public Integer getMo() {
        return mo;
    }

    public void setMo(Integer mo) {
        this.mo = mo;
    }

    public Integer getSmo() {
        return smo;
    }

    public void setSmo(Integer smo) {
        this.smo = smo;
    }

    public Integer getPmo() {
        return pmo;
    }

    public void setPmo(Integer pmo) {
        this.pmo = pmo;
    }

    public Integer getJrm() {
        return jrm;
    }

    public void setJrm(Integer jrm) {
        this.jrm = jrm;
    }

    public Integer getArm() {
        return arm;
    }

    public void setArm(Integer arm) {
        this.arm = arm;
    }

    public Integer getRm() {
        return rm;
    }

    public void setRm(Integer rm) {
        this.rm = rm;
    }

    public Integer getSrm() {
        return srm;
    }

    public void setSrm(Integer srm) {
        this.srm = srm;
    }

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}