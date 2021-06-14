package com.project.integratedservices.repository.integratedServicesForAllTypes.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlanDetailsResponse implements Parcelable
{

    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("link")
    @Expose
    private String link;
    public final static Parcelable.Creator<PlanDetailsResponse> CREATOR = new Creator<PlanDetailsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PlanDetailsResponse createFromParcel(Parcel in) {
            return new PlanDetailsResponse(in);
        }

        public PlanDetailsResponse[] newArray(int size) {
            return (new PlanDetailsResponse[size]);
        }

    }
            ;

    protected PlanDetailsResponse(Parcel in) {
        this.productName = ((String) in.readValue((String.class.getClassLoader())));
        this.link = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PlanDetailsResponse() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productName);
        dest.writeValue(link);
    }

    public int describeContents() {
        return 0;
    }

}