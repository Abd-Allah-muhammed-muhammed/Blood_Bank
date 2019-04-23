
package com.abdo.hp.bank.model.general.logs;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class LogsDatum {

    @SerializedName("content")
    private LogsContent mLogsContent;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("id")
    private Long mId;
    @SerializedName("service")
    private String mService;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public LogsContent getContent() {
        return mLogsContent;
    }

    public void setContent(LogsContent logsContent) {
        mLogsContent = logsContent;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getService() {
        return mService;
    }

    public void setService(String service) {
        mService = service;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
