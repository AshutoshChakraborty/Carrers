package com.project.integratedservices.repository.integratedServicesForAllTypes.response.menu_status_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenueStatusResponse {
    @SerializedName("MenuName")
    @Expose
    private String menuName;
    @SerializedName("ViewType")
    @Expose
    private String viewType;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
