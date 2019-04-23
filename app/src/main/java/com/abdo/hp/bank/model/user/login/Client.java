
package com.abdo.hp.bank.model.user.login;

import com.abdo.hp.bank.model.City;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Client {

    @SerializedName("birth_date")
    private String mBirthDate;
    @SerializedName("blood_type")
    private BloodType mBloodType;
    @SerializedName("blood_type_id")
    private String mBloodTypeId;
    @SerializedName("city")
    private City mCity;
    @SerializedName("city_id")
    private String mCityId;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("donation_last_date")
    private String mDonationLastDate;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("id")
    private Long mId;
    @SerializedName("is_active")
    private String mIsActive;
    @SerializedName("name")
    private String mName;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("pin_code")
    private String mPinCode;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public String getBirthDate() {
        return mBirthDate;
    }

    public void setBirthDate(String birthDate) {
        mBirthDate = birthDate;
    }

    public BloodType getBloodType() {
        return mBloodType;
    }

    public void setBloodType(BloodType bloodType) {
        mBloodType = bloodType;
    }

    public String getBloodTypeId() {
        return mBloodTypeId;
    }

    public void setBloodTypeId(String bloodTypeId) {
        mBloodTypeId = bloodTypeId;
    }

    public City getCity() {
        return mCity;
    }

    public void setCity(City city) {
        mCity = city;
    }

    public String getCityId() {
        return mCityId;
    }

    public void setCityId(String cityId) {
        mCityId = cityId;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getDonationLastDate() {
        return mDonationLastDate;
    }

    public void setDonationLastDate(String donationLastDate) {
        mDonationLastDate = donationLastDate;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getIsActive() {
        return mIsActive;
    }

    public void setIsActive(String isActive) {
        mIsActive = isActive;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getPinCode() {
        return mPinCode;
    }

    public void setPinCode(String pinCode) {
        mPinCode = pinCode;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
