
package com.abdo.hp.bank.model.general.listof_governorate;

import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ListOfGovernorate {

    @SerializedName("data")
    private List<ListOfGovernorateDatum> mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public List<ListOfGovernorateDatum> getData() {
        return mData;
    }

    public void setData(List<ListOfGovernorateDatum> data) {
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
