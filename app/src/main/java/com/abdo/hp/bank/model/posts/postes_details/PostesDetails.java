
package com.abdo.hp.bank.model.posts.postes_details;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PostesDetails {

    @SerializedName("data")
    private PostesDetailsData mPostesDetailsData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public PostesDetailsData getData() {
        return mPostesDetailsData;
    }

    public void setData(PostesDetailsData postesDetailsData) {
        mPostesDetailsData = postesDetailsData;
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
