package com.project.integratedservices.authenticate.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.integratedservices.repository.authencationRepo.remote.request.LoginRequestModel;
import com.project.integratedservices.repository.authencationRepo.remote.response.AttendanceCheckResponse;
import com.project.integratedservices.repository.authencationRepo.remote.response.DailyAttendanceCheck;
import com.project.integratedservices.repository.authencationRepo.remote.response.DailyAttendanceTime;
import com.project.integratedservices.repository.authencationRepo.remote.response.loginModel.LoginResponse;
import com.project.integratedservices.repository.authencationRepo.remote.response.userDetails.UserDetailsResponse;
import com.project.integratedservices.retofit.RemoteClient;
import com.project.integratedservices.retofit.RetrofitApis;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AuthenticationViewModel extends ViewModel {
    private RetrofitApis apiClient = RemoteClient.getRetrofitApi();
    private MutableLiveData<LoginResponse> loginResponseModelMutableLiveData;
    private MutableLiveData<UserDetailsResponse> userDetailsResponseMutableLiveData;
    private MutableLiveData<DailyAttendanceCheck> attendanceCheckResponseMutableLiveData;
    private MutableLiveData<DailyAttendanceTime> attendanceTimeResponseMutableLiveData;
    private MutableLiveData<String> apiError;

    public MutableLiveData<LoginResponse> getLoginResponse() {

        if (loginResponseModelMutableLiveData == null) {
            loginResponseModelMutableLiveData = new MutableLiveData<>();
        }
        return loginResponseModelMutableLiveData;

    }

    public MutableLiveData<UserDetailsResponse> getUserDetails() {

        if (userDetailsResponseMutableLiveData == null) {
            userDetailsResponseMutableLiveData = new MutableLiveData<>();
        }
        return userDetailsResponseMutableLiveData;

    }

    public MutableLiveData<DailyAttendanceCheck> attendanceCheckResponse() {

        if (attendanceCheckResponseMutableLiveData == null) {
            attendanceCheckResponseMutableLiveData = new MutableLiveData<>();
        }
        return attendanceCheckResponseMutableLiveData;

    }

    public MutableLiveData<DailyAttendanceTime> attendanceTimeResponse() {

        if (attendanceTimeResponseMutableLiveData == null) {
            attendanceTimeResponseMutableLiveData = new MutableLiveData<>();
        }
        return attendanceTimeResponseMutableLiveData;

    }

    public MutableLiveData<String> getApiError() {

        if (apiError == null) {
            apiError = new MutableLiveData<>();
        }
        return apiError;

    }

    public void callLoginApi(LoginRequestModel model)
    {
//        apiClient.callLogIn(model.getUserid(),model.getPassowrd(),model.getImeiCode(),model.getTokenid()).enqueue(new Callback<List<LoginResponse>>() {
//            @Override
//            public void onResponse(Call<List<LoginResponse>> call, Response<List<LoginResponse>> response) {
//                loginResponseModelMutableLiveData.setValue(response.body().get(0));
//            }
//
//            @Override
//            public void onFailure(Call<List<LoginResponse>> call, Throwable t) {
//                apiError.setValue(t.getLocalizedMessage());
//            }
//        });

        apiClient.callLogIn(model.getAgentCode()).enqueue(new Callback<List<LoginResponse>>() {
            @Override
            public void onResponse(Call<List<LoginResponse>> call, Response<List<LoginResponse>> response) {
                loginResponseModelMutableLiveData.setValue(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<LoginResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void callUserDetails(String agentCode,String iemiCode,String token)
    {
        Log.i("agentCode: ",agentCode);
        Log.i("iemiCode: ",iemiCode);
        Log.i("token: ",token);
        apiClient.getUserDetails(agentCode,iemiCode,token).enqueue(new Callback<List<UserDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<UserDetailsResponse>> call, Response<List<UserDetailsResponse>> response) {
                if (response.body()!=null) {
                    userDetailsResponseMutableLiveData.setValue(response.body().get(0));
                }
            }

            @Override
            public void onFailure(Call<List<UserDetailsResponse>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void checkAttendance(String agentCode)
    {
        apiClient.attendanceStatusCheck(agentCode).enqueue(new Callback<List<DailyAttendanceCheck>>() {
            @Override
            public void onResponse(Call<List<DailyAttendanceCheck>> call, Response<List<DailyAttendanceCheck>> response) {
                if(response.body()!=null)
                attendanceCheckResponseMutableLiveData.setValue(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<DailyAttendanceCheck>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

    public void checkAttendanceTime(String agentCode)
    {
        apiClient.attendanceTime(agentCode).enqueue(new Callback<List<DailyAttendanceTime>>() {
            @Override
            public void onResponse(Call<List<DailyAttendanceTime>> call, Response<List<DailyAttendanceTime>> response) {
                if(response.body()!=null)
                attendanceTimeResponseMutableLiveData.setValue(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<DailyAttendanceTime>> call, Throwable t) {
                apiError.setValue(t.getLocalizedMessage());
            }
        });
    }

}
