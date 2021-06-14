package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendanceDetailsResponsePojo implements Parcelable {

    @SerializedName("AttendanceDate")
    @Expose
    private String attendanceDate;
    @SerializedName("Status")
    @Expose
    private String status;
    public final static Parcelable.Creator<AttendanceDetailsResponsePojo> CREATOR = new Creator<AttendanceDetailsResponsePojo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AttendanceDetailsResponsePojo createFromParcel(Parcel in) {
            return new AttendanceDetailsResponsePojo(in);
        }

        public AttendanceDetailsResponsePojo[] newArray(int size) {
            return (new AttendanceDetailsResponsePojo[size]);
        }

    }
            ;

    protected AttendanceDetailsResponsePojo(Parcel in) {
        this.attendanceDate = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AttendanceDetailsResponsePojo() {
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(attendanceDate);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}
