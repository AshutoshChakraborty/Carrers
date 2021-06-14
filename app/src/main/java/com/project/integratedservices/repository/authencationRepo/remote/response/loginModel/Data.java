package com.project.integratedservices.repository.authencationRepo.remote.response.loginModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("userDetails")
    @Expose
    private UserDetails userDetails;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}