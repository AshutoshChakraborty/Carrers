package com.project.integratedservices.repository.authencationRepo.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyAttendanceCheck {
    @SerializedName("StartAtt")
    @Expose
    private String startAttendance;

    @SerializedName("EndAtt")
    @Expose
    private String endAttendance;

    @SerializedName("ActiveAttendance")
    @Expose
    private String activeAttendance;

    public String getStartAttendance() {
        return startAttendance;
    }

    public void setStartAttendance(String startAttendance) {
        this.startAttendance = startAttendance;
    }

    public String getEndAttendance() {
        return endAttendance;
    }

    public void setEndAttendance(String endAttendance) {
        this.endAttendance = endAttendance;
}

    public String getActiveAttendance() {
        return activeAttendance;
    }

    public void setActiveAttendance(String activeAttendance) {
        this.activeAttendance = activeAttendance;
    }
}
