
package com.abdo.hp.bank.model.notification.notificationsettings;


import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class NotificationSettings {

    @SerializedName("data")
    private NotificationData mNotificationData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public NotificationData getData() {
        return mNotificationData;
    }

    public void setData(NotificationData notificationData) {
        mNotificationData = notificationData;
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
