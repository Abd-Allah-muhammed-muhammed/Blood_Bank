
package com.abdo.hp.bank.model.posts.posts_favorete_list;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PostesFavouriteList {

    @SerializedName("data")
    private PostesFavouriteListData mPostesFavouriteListData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("status")
    private Long mStatus;

    public PostesFavouriteListData getData() {
        return mPostesFavouriteListData;
    }

    public void setData(PostesFavouriteListData postesFavouriteListData) {
        mPostesFavouriteListData = postesFavouriteListData;
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
