package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendanceResponse implements Parcelable
{

    @SerializedName("AgentCode")
    @Expose
    private String agentCode;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("start_latitude")
    @Expose
    private String startLatitude;
    @SerializedName("start_longitude")
    @Expose
    private String startLongitude;
    @SerializedName("Status")
    @Expose
    private String status;
    public final static Parcelable.Creator<AttendanceResponse> CREATOR = new Creator<AttendanceResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AttendanceResponse createFromParcel(Parcel in) {
            return new AttendanceResponse(in);
        }

        public AttendanceResponse[] newArray(int size) {
            return (new AttendanceResponse[size]);
        }

    }
            ;

    protected AttendanceResponse(Parcel in) {
        this.agentCode = ((String) in.readValue((String.class.getClassLoader())));
        this.startDate = ((String) in.readValue((String.class.getClassLoader())));
        this.startLatitude = ((String) in.readValue((String.class.getClassLoader())));
        this.startLongitude = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AttendanceResponse() {
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(String startLatitude) {
        this.startLatitude = startLatitude;
    }

    public String getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(String startLongitude) {
        this.startLongitude = startLongitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(agentCode);
        dest.writeValue(startDate);
        dest.writeValue(startLatitude);
        dest.writeValue(startLongitude);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}
