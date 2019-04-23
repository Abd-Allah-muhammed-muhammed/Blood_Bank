
package com.abdo.hp.bank.model.general.list_of_cities;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ListOfCities {

    @SerializedName("data")
    private List<ListOfCitiesDatum> mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public List<ListOfCitiesDatum> getData() {
        return mData;
    }

    public void setData(List<ListOfCitiesDatum> data) {
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
