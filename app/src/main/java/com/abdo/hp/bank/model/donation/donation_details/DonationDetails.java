
package com.abdo.hp.bank.model.donation.donation_details;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DonationDetails {

    @SerializedName("data")
    private DonationDetailsData mDonationDetailsData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public DonationDetailsData getData() {
        return mDonationDetailsData;
    }

    public void setData(DonationDetailsData donationDetailsData) {
        mDonationDetailsData = donationDetailsData;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

}
