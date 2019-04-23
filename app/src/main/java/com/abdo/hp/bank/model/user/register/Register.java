
package com.abdo.hp.bank.model.user.register;

import com.google.gson.annotations.SerializedName;

public class Register {

    @SerializedName("data")
    private RegisterData mRegisterData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public RegisterData getData() {
        return mRegisterData;
    }

    public void setData(RegisterData registerData) {
        mRegisterData = registerData;
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
