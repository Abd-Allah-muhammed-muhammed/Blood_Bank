
package com.abdo.hp.bank.model.notification.notification_list;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class NotificationList {

    @SerializedName("data")
    private NotificationListData mNotificationListData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public NotificationListData getData() {
        return mNotificationListData;
    }

    public void setData(NotificationListData notificationListData) {
        mNotificationListData = notificationListData;
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
