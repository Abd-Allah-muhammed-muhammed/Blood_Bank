
package com.abdo.hp.bank.model.user.profile;


import com.abdo.hp.bank.model.user.login.LoginClient;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("user")
    private LoginClient mUser;

    public LoginClient getUser() {
        return mUser;
    }

    public void setUser(LoginClient user) {
        mUser = user;
    }

}
