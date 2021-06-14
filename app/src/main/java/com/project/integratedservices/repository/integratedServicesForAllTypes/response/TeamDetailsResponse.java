package com.project.integratedservices.repository.integratedServicesForAllTypes.response;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamDetailsResponse implements Parcelable {

    @SerializedName("Agent_Code")
    @Expose
    private String agentCode;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Code")
    @Expose
    private String Code;

    @SerializedName("Ag_Rank_ID")
    @Expose
    private String Rank;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Grade")
    @Expose
    private String grade;

    private boolean isSelected;

    public final static Parcelable.Creator<TeamDetailsResponse> CREATOR = new Creator<TeamDetailsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TeamDetailsResponse createFromParcel(Parcel in) {
            return new TeamDetailsResponse(in);
        }

        public TeamDetailsResponse[] newArray(int size) {
            return (new TeamDetailsResponse[size]);
        }

    };

    protected TeamDetailsResponse(Parcel in) {
        this.agentCode = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.Rank = ((String) in.readValue((String.class.getClassLoader())));
        this.Code = ((String) in.readValue((String.class.getClassLoader())));
        this.grade = ((String) in.readValue((String.class.getClassLoader())));
    }

    public TeamDetailsResponse() {
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(agentCode);
        dest.writeValue(name);
        dest.writeValue(type);
        dest.writeValue(status);
        dest.writeValue(Rank);
        dest.writeValue(Code);
        dest.writeValue(grade);
    }

    public int describeContents() {
        return 0;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}