
package com.abdo.hp.bank.model.general.connect_us;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ConnectUs {

    @SerializedName("data")
    private ConnectUsData mConnectUsData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public ConnectUsData getData() {
        return mConnectUsData;
    }

    public void setData(ConnectUsData connectUsData) {
        mConnectUsData = connectUsData;
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
