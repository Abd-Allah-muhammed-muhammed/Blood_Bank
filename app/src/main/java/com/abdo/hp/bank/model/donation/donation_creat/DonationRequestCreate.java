
package com.abdo.hp.bank.model.donation.donation_creat;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DonationRequestCreate {

    @SerializedName("data")
    private DonationRequestCreateData mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public DonationRequestCreateData getData() {
        return mData;
    }

    public void setData(DonationRequestCreateData data) {
        mData = data;
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
