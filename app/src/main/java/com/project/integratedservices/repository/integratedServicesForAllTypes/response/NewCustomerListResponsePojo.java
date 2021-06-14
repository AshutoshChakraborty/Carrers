package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewCustomerListResponsePojo implements Parcelable
{

    @SerializedName("CustomerId")
    @Expose
    private String customerId;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Contact")
    @Expose
    private String contact;
    @SerializedName("Status")
    @Expose
    private String status;
    public final static Parcelable.Creator<NewCustomerListResponsePojo> CREATOR = new Creator<NewCustomerListResponsePojo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NewCustomerListResponsePojo createFromParcel(Parcel in) {
            return new NewCustomerListResponsePojo(in);
        }

        public NewCustomerListResponsePojo[] newArray(int size) {
            return (new NewCustomerListResponsePojo[size]);
        }

    }
            ;

    protected NewCustomerListResponsePojo(Parcel in) {
        this.customerId = ((String) in.readValue((String.class.getClassLoader())));
        this.customerName = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.contact = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public NewCustomerListResponsePojo() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(customerId);
        dest.writeValue(customerName);
        dest.writeValue(address);
        dest.writeValue(contact);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}