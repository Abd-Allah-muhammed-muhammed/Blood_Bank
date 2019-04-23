
package com.abdo.hp.bank.model.notification.notification_list;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")

public class NotificationListDatum {

    @SerializedName("content")
    private String mContent;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("donation_request_id")
    private String mDonationRequestId;
    @SerializedName("id")
    private Long mId;
    @SerializedName("pivot")
    private NotificationListPivot mNotificationListPivot;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getDonationRequestId() {
        return mDonationRequestId;
    }

    public void setDonationRequestId(String donationRequestId) {
        mDonationRequestId = donationRequestId;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public NotificationListPivot getPivot() {
        return mNotificationListPivot;
    }

    public void setPivot(NotificationListPivot notificationListPivot) {
        mNotificationListPivot = notificationListPivot;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
