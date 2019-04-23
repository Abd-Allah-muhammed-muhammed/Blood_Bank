
package com.abdo.hp.bank.model.notification.notificationToken;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class NotificationToken {

    @SerializedName("data")
    private Object mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public Object getData() {
        return mData;
    }

    public void setData(Object data) {
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
