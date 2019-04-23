
package com.abdo.hp.bank.model.general.report;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Report {

    @SerializedName("data")
    private ReportData mReportData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public ReportData getData() {
        return mReportData;
    }

    public void setData(ReportData reportData) {
        mReportData = reportData;
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
