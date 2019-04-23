
package com.abdo.hp.bank.model.notification.notificationsettings;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class NotificationData {

    @SerializedName("blood_types")
    private List<String> mBloodTypes;
    @SerializedName("governorates")
    private List<String> mCities;

    public List<String> getBloodTypes() {
        return mBloodTypes;
    }

    public void setBloodTypes(List<String> bloodTypes) {
        mBloodTypes = bloodTypes;
    }

    public List<String> getCities() {
        return mCities;
    }

    public void setCities(List<String> cities) {
        mCities = cities;
    }

}
