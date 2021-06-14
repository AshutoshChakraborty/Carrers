package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssignCustomerResponsePojo implements Parcelable
{

    @SerializedName("Customer_Name")
    @Expose
    private String customerName;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Pin_Code")
    @Expose
    private String pinCode;
    @SerializedName("Mobile_Number")
    @Expose
    private String mobileNumber;
    @SerializedName("Email_ID")
    @Expose
    private String emailID;
    @SerializedName("Status")
    @Expose
    private String status;
    public final static Parcelable.Creator<AssignCustomerResponsePojo> CREATOR = new Creator<AssignCustomerResponsePojo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AssignCustomerResponsePojo createFromParcel(Parcel in) {
            return new AssignCustomerResponsePojo(in);
        }

        public AssignCustomerResponsePojo[] newArray(int size) {
            return (new AssignCustomerResponsePojo[size]);
        }

    }
            ;

    protected AssignCustomerResponsePojo(Parcel in) {
        this.customerName = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.pinCode = ((String) in.readValue((String.class.getClassLoader())));
        this.mobileNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.emailID = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AssignCustomerResponsePojo() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(customerName);
        dest.writeValue(address);
        dest.writeValue(pinCode);
        dest.writeValue(mobileNumber);
        dest.writeValue(emailID);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}
