
package com.abdo.hp.bank.model.notification.notification_count;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class NotificationCount {

    @SerializedName("data")
    private NotificationCountData mNotificationCountData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public NotificationCountData getData() {
        return mNotificationCountData;
    }

    public void setData(NotificationCountData notificationCountData) {
        mNotificationCountData = notificationCountData;
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
