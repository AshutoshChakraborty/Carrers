package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesDetailsResponsePojo implements Parcelable
{

    @SerializedName("Customer_ID")
    @Expose
    private String customerID;
    @SerializedName("Customer_Name")
    @Expose
    private String customerName;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("New")
    @Expose
    private String _new;
    @SerializedName("FollowUp")
    @Expose
    private String followUp;
    @SerializedName("Closed")
    @Expose
    private String closed;
    @SerializedName("Status")
    @Expose
    private String status;
    public final static Parcelable.Creator<SalesDetailsResponsePojo> CREATOR = new Creator<SalesDetailsResponsePojo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SalesDetailsResponsePojo createFromParcel(Parcel in) {
            return new SalesDetailsResponsePojo(in);
        }

        public SalesDetailsResponsePojo[] newArray(int size) {
            return (new SalesDetailsResponsePojo[size]);
        }

    }
            ;

    protected SalesDetailsResponsePojo(Parcel in) {
        this.customerID = ((String) in.readValue((String.class.getClassLoader())));
        this.customerName = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.phone = ((String) in.readValue((String.class.getClassLoader())));
        this._new = ((String) in.readValue((String.class.getClassLoader())));
        this.followUp = ((String) in.readValue((String.class.getClassLoader())));
        this.closed = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SalesDetailsResponsePojo() {
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNew() {
        return _new;
    }

    public void setNew(String _new) {
        this._new = _new;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(customerID);
        dest.writeValue(customerName);
        dest.writeValue(address);
        dest.writeValue(phone);
        dest.writeValue(_new);
        dest.writeValue(followUp);
        dest.writeValue(closed);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}