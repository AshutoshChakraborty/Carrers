package com.project.integratedservices.repository.integratedServicesForAllTypes.request;

public class ApplyLeaveRequestPojo {
    private String AgentCode, LeaveText, LeaveStartDate, LeaveEndDate;


    public String getAgentCode() {
        return AgentCode;
    }

    public void setAgentCode(String agentCode) {
        AgentCode = agentCode;
    }

    public String getLeaveText() {
        return LeaveText;
    }

    public void setLeaveText(String leaveText) {
        LeaveText = leaveText;
    }

    public String getLeaveStartDate() {
        return LeaveStartDate;
    }

    public void setLeaveStartDate(String leaveStartDate) {
        LeaveStartDate = leaveStartDate;
    }

    public String getLeaveEndDate() {
        return LeaveEndDate;
    }

    public void setLeaveEndDate(String leaveEndDate) {
        LeaveEndDate = leaveEndDate;
    }
}
