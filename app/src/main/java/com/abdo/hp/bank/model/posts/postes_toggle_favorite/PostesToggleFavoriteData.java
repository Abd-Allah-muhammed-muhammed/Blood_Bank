
package com.abdo.hp.bank.model.posts.postes_toggle_favorite;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PostesToggleFavoriteData {

    @SerializedName("attached")
    private List<Long> mAttached;
    @SerializedName("detached")
    private List<Object> mDetached;

    public List<Long> getAttached() {
        return mAttached;
    }

    public void setAttached(List<Long> attached) {
        mAttached = attached;
    }

    public List<Object> getDetached() {
        return mDetached;
    }

    public void setDetached(List<Object> detached) {
        mDetached = detached;
    }

}
