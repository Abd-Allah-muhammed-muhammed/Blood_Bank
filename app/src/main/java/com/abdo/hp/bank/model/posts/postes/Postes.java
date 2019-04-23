
package com.abdo.hp.bank.model.posts.postes;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Postes {

    @SerializedName("data")
    private PostesData mPostesData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public PostesData getData() {
        return mPostesData;
    }

    public void setData(PostesData postesData) {
        mPostesData = postesData;
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
