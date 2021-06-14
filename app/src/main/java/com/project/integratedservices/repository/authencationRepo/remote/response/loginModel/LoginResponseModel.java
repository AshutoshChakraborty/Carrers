package com.project.integratedservices.repository.authencationRepo.remote.response.loginModel;

import java.util.List;

public class LoginResponseModel {
    public List<LoginResponse> getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(List<LoginResponse> loginResponse) {
        this.loginResponse = loginResponse;
    }

    private List<LoginResponse> loginResponse;


}
