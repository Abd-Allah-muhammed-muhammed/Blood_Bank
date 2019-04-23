
package com.abdo.hp.bank.model.posts.posts_favorete_list;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PostesFavouriteListPivot {

    @SerializedName("client_id")
    private String mClientId;
    @SerializedName("post_id")
    private String mPostId;

    public String getClientId() {
        return mClientId;
    }

    public void setClientId(String clientId) {
        mClientId = clientId;
    }

    public String getPostId() {
        return mPostId;
    }

    public void setPostId(String postId) {
        mPostId = postId;
    }

}
