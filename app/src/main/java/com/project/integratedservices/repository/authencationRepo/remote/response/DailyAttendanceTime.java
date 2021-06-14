package com.project.integratedservices.repository.authencationRepo.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyAttendanceTime implements Parcelable
{

    @SerializedName("StartTime")
    @Expose
    private String startTime;
    @SerializedName("EndTime")
    @Expose
    private String endTime;
    public final static Parcelable.Creator<DailyAttendanceTime> CREATOR = new Creator<DailyAttendanceTime>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DailyAttendanceTime createFromParcel(Parcel in) {
            return new DailyAttendanceTime(in);
        }

        public DailyAttendanceTime[] newArray(int size) {
            return (new DailyAttendanceTime[size]);
        }

    }
            ;

    protected DailyAttendanceTime(Parcel in) {
        this.startTime = ((String) in.readValue((String.class.getClassLoader())));
        this.endTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DailyAttendanceTime() {
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(startTime);
        dest.writeValue(endTime);
    }

    public int describeContents() {
        return 0;
    }

}