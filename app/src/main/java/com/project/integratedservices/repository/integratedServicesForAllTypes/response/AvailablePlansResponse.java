package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvailablePlansResponse implements Parcelable
{

    @SerializedName("company_name")
    @Expose
    private String companyName;
    public final static Parcelable.Creator<AvailablePlansResponse> CREATOR = new Creator<AvailablePlansResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AvailablePlansResponse createFromParcel(Parcel in) {
            return new AvailablePlansResponse(in);
        }

        public AvailablePlansResponse[] newArray(int size) {
            return (new AvailablePlansResponse[size]);
        }

    }
            ;

    protected AvailablePlansResponse(Parcel in) {
        this.companyName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AvailablePlansResponse() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(companyName);
    }

    public int describeContents() {
        return 0;
    }

}