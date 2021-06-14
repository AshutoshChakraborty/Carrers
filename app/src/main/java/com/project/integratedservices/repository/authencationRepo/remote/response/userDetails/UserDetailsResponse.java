package com.project.integratedservices.repository.authencationRepo.remote.response.userDetails;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetailsResponse implements Parcelable {

    public final static Creator<UserDetailsResponse> CREATOR = new Creator<UserDetailsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserDetailsResponse createFromParcel(Parcel in) {
            return new UserDetailsResponse(in);
        }

        public UserDetailsResponse[] newArray(int size) {
            return (new UserDetailsResponse[size]);
        }

    };
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("imei_code")
    @Expose
    private String imeiCode;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("agentType")
    @Expose
    private String agentType;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("BranchName")
    @Expose
    private String branchName;
    @SerializedName("IntroName")
    @Expose
    private String introName;

    @SerializedName("IntroCode")
    @Expose
    private String IntroCode;

    @SerializedName("IntroBranch")
    @Expose
    private String IntroBranch;
    @SerializedName("LoginType")
    @Expose
    private String loginType;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("LeaveStatus")
    @Expose
    private String leaveStatus;
    @SerializedName("RoleId")
    @Expose
    private String roleId;

    @SerializedName("CompanyName")
    @Expose
    private String companyName;

    protected UserDetailsResponse(Parcel in) {
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        this.password = ((String) in.readValue((String.class.getClassLoader())));
        this.token = ((String) in.readValue((String.class.getClassLoader())));
        this.imeiCode = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.contact = ((String) in.readValue((String.class.getClassLoader())));
        this.agentType = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.branchName = ((String) in.readValue((String.class.getClassLoader())));
        this.introName = ((String) in.readValue((String.class.getClassLoader())));
        this.loginType = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.leaveStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.roleId = ((String) in.readValue((String.class.getClassLoader())));
        this.IntroCode = ((String) in.readValue((String.class.getClassLoader())));
        this.IntroBranch = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UserDetailsResponse() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getImeiCode() {
        return imeiCode;
    }

    public void setImeiCode(String imeiCode) {
        this.imeiCode = imeiCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIntroName() {
        return introName;
    }

    public void setIntroName(String introName) {
        this.introName = introName;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userName);
        dest.writeValue(password);
        dest.writeValue(token);
        dest.writeValue(imeiCode);
        dest.writeValue(address);
        dest.writeValue(email);
        dest.writeValue(contact);
        dest.writeValue(agentType);
        dest.writeValue(gender);
        dest.writeValue(branchName);
        dest.writeValue(introName);
        dest.writeValue(loginType);
        dest.writeValue(status);
        dest.writeValue(leaveStatus);
        dest.writeValue(roleId);
        dest.writeValue(IntroCode);
        dest.writeValue(IntroBranch);
    }

    public int describeContents() {
        return 0;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIntroCode() {
        return IntroCode;
    }

    public void setIntroCode(String introCode) {
        IntroCode = introCode;
    }

    public String getIntroBranch() {
        return IntroBranch;
    }

    public void setIntroBranch(String introBranch) {
        IntroBranch = introBranch;
    }
}