
package com.abdo.hp.bank.model.posts.postes_details;

import com.abdo.hp.bank.model.posts.postes.PostesCategory;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PostesDetailsData {

    @SerializedName("category")
    private PostesCategory mPostesDetailsCategory;
    @SerializedName("category_id")
    private String mCategoryId;
    @SerializedName("content")
    private String mContent;
    @SerializedName("created_at")
    private Object mCreatedAt;
    @SerializedName("id")
    private Long mId;
    @SerializedName("is_favourite")
    private Boolean mIsFavourite;
    @SerializedName("publish_date")
    private String mPublishDate;
    @SerializedName("thumbnail")
    private String mThumbnail;
    @SerializedName("thumbnail_full_path")
    private String mThumbnailFullPath;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("updated_at")
    private Object mUpdatedAt;

    public PostesCategory getCategory() {
        return mPostesDetailsCategory;
    }

    public void setCategory(PostesCategory postesDetailsCategory) {
        mPostesDetailsCategory = postesDetailsCategory;
    }

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        mCategoryId = categoryId;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public Object getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Object createdAt) {
        mCreatedAt = createdAt;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Boolean getIsFavourite() {
        return mIsFavourite;
    }

    public void setIsFavourite(Boolean isFavourite) {
        mIsFavourite = isFavourite;
    }

    public String getPublishDate() {
        return mPublishDate;
    }

    public void setPublishDate(String publishDate) {
        mPublishDate = publishDate;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(String thumbnail) {
        mThumbnail = thumbnail;
    }

    public String getThumbnailFullPath() {
        return mThumbnailFullPath;
    }

    public void setThumbnailFullPath(String thumbnailFullPath) {
        mThumbnailFullPath = thumbnailFullPath;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Object getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
