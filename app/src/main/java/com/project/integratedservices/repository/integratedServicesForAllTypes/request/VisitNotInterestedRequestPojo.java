package com.project.integratedservices.repository.integratedServicesForAllTypes.request;

public class VisitNotInterestedRequestPojo {
    private String AgentCode,
            CustomerId,
            Comments;

    public String getAgentCode() {
        return AgentCode;
    }

    public void setAgentCode(String agentCode) {
        AgentCode = agentCode;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }
}
