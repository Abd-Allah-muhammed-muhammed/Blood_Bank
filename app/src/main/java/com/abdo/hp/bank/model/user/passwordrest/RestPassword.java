
package com.abdo.hp.bank.model.user.passwordrest;

import com.google.gson.annotations.SerializedName;

public class RestPassword {

    @SerializedName("data")
    private RestPasswordData mRestPasswordData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public RestPasswordData getData() {
        return mRestPasswordData;
    }

    public void setData(RestPasswordData restPasswordData) {
        mRestPasswordData = restPasswordData;
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
