
package com.abdo.hp.bank.model.notification.notification_list;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class NotificationListPivot {

    @SerializedName("client_id")
    private String mClientId;
    @SerializedName("is_read")
    private String mIsRead;
    @SerializedName("notification_id")
    private String mNotificationId;

    public String getClientId() {
        return mClientId;
    }

    public void setClientId(String clientId) {
        mClientId = clientId;
    }

    public String getIsRead() {
        return mIsRead;
    }

    public void setIsRead(String isRead) {
        mIsRead = isRead;
    }

    public String getNotificationId() {
        return mNotificationId;
    }

    public void setNotificationId(String notificationId) {
        mNotificationId = notificationId;
    }

}
