
package com.abdo.hp.bank.model.notification.notification_count;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class NotificationCountData {

    @SerializedName("notifications-count")
    private Long mNotificationsCount;

    public Long getNotificationsCount() {
        return mNotificationsCount;
    }

    public void setNotificationsCount(Long notificationsCount) {
        mNotificationsCount = notificationsCount;
    }

}
