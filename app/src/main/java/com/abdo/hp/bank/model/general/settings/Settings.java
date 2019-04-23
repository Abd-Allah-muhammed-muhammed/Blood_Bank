
package com.abdo.hp.bank.model.general.settings;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Settings {

    @SerializedName("data")
    private SettingsData mSettingsData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public SettingsData getData() {
        return mSettingsData;
    }

    public void setData(SettingsData settingsData) {
        mSettingsData = settingsData;
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
