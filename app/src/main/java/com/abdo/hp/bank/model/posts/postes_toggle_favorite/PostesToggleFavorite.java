
package com.abdo.hp.bank.model.posts.postes_toggle_favorite;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PostesToggleFavorite {

    @SerializedName("data")
    private PostesToggleFavoriteData mPostesToggleFavoriteData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public PostesToggleFavoriteData getData() {
        return mPostesToggleFavoriteData;
    }

    public void setData(PostesToggleFavoriteData postesToggleFavoriteData) {
        mPostesToggleFavoriteData = postesToggleFavoriteData;
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
