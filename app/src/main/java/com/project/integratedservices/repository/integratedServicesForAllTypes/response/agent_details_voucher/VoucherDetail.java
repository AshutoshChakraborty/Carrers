package com.project.integratedservices.repository.integratedServicesForAllTypes.response.agent_details_voucher;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VoucherDetail {

    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("DESCRIPTION")
    @Expose
    private String description;
    @SerializedName("STATEMENT_DATE")
    @Expose
    private String statementDate;
    @SerializedName("FRMREG")
    @Expose
    private String frmreg;
    @SerializedName("FRMNO")
    @Expose
    private String frmno;
    @SerializedName("SCHEME")
    @Expose
    private String scheme;
    @SerializedName("FRMAMT")
    @Expose
    private String frmamt;
    @SerializedName("PERCENT")
    @Expose
    private String percent;
    @SerializedName("FRMCOMM")
    @Expose
    private String frmcomm;
    @SerializedName("FRMSPOT")
    @Expose
    private String frmspot;
    @SerializedName("FRMADJ")
    @Expose
    private String frmadj;
    @SerializedName("INVESTOR")
    @Expose
    private String investor;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(String statementDate) {
        this.statementDate = statementDate;
    }

    public String getFrmreg() {
        return frmreg;
    }

    public void setFrmreg(String frmreg) {
        this.frmreg = frmreg;
    }

    public String getFrmno() {
        return frmno;
    }

    public void setFrmno(String frmno) {
        this.frmno = frmno;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getFrmamt() {
        return frmamt;
    }

    public void setFrmamt(String frmamt) {
        this.frmamt = frmamt;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getFrmcomm() {
        return frmcomm;
    }

    public void setFrmcomm(String frmcomm) {
        this.frmcomm = frmcomm;
    }

    public String getFrmspot() {
        return frmspot;
    }

    public void setFrmspot(String frmspot) {
        this.frmspot = frmspot;
    }

    public String getFrmadj() {
        return frmadj;
    }

    public void setFrmadj(String frmadj) {
        this.frmadj = frmadj;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
