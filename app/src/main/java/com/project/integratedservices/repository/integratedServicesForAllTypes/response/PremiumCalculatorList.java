package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PremiumCalculatorList implements Parcelable
{

    @SerializedName("insurer_name")
    @Expose
    private String insurerName;
    @SerializedName("insurer_logo")
    @Expose
    private String insurerLogo;
    @SerializedName("premium_link")
    @Expose
    private String premiumLink;
    @SerializedName("Status")
    @Expose
    private String status;
    public final static Parcelable.Creator<PremiumCalculatorList> CREATOR = new Parcelable.Creator<PremiumCalculatorList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PremiumCalculatorList createFromParcel(Parcel in) {
            return new PremiumCalculatorList(in);
        }

        public PremiumCalculatorList[] newArray(int size) {
            return (new PremiumCalculatorList[size]);
        }

    }
            ;

    protected PremiumCalculatorList(Parcel in) {
        this.insurerName = ((String) in.readValue((String.class.getClassLoader())));
        this.insurerLogo = ((String) in.readValue((String.class.getClassLoader())));
        this.premiumLink = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PremiumCalculatorList() {
    }

    public String getInsurerName() {
        return insurerName;
    }

    public void setInsurerName(String insurerName) {
        this.insurerName = insurerName;
    }

    public String getInsurerLogo() {
        return insurerLogo;
    }

    public void setInsurerLogo(String insurerLogo) {
        this.insurerLogo = insurerLogo;
    }

    public String getPremiumLink() {
        return premiumLink;
    }

    public void setPremiumLink(String premiumLink) {
        this.premiumLink = premiumLink;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(insurerName);
        dest.writeValue(insurerLogo);
        dest.writeValue(premiumLink);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}