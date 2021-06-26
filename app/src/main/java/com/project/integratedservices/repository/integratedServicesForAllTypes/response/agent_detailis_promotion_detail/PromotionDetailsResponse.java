package com.project.integratedservices.repository.integratedServicesForAllTypes.response.agent_detailis_promotion_detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromotionDetailsResponse {
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Rank")
    @Expose
    private String rank;
    @SerializedName("New_Rank")
    @Expose
    private String newRank;
    @SerializedName("Old_Rank")
    @Expose
    private String oldRank;
    @SerializedName("Old_Intro")
    @Expose
    private String oldIntro;
    @SerializedName("New_Intro")
    @Expose
    private String newIntro;
    @SerializedName("Added_By")
    @Expose
    private String addedBy;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getNewRank() {
        return newRank;
    }

    public void setNewRank(String newRank) {
        this.newRank = newRank;
    }

    public String getOldRank() {
        return oldRank;
    }

    public void setOldRank(String oldRank) {
        this.oldRank = oldRank;
    }

    public String getOldIntro() {
        return oldIntro;
    }

    public void setOldIntro(String oldIntro) {
        this.oldIntro = oldIntro;
    }

    public String getNewIntro() {
        return newIntro;
    }

    public void setNewIntro(String newIntro) {
        this.newIntro = newIntro;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
