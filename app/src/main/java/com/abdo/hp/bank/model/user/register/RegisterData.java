
package com.abdo.hp.bank.model.user.register;

import com.google.gson.annotations.SerializedName;


public class RegisterData {

    @SerializedName("api_token")
    private String mApiToken;
    @SerializedName("client")
    private RegisterClient mRegisterClient;

    public String getApiToken() {
        return mApiToken;
    }

    public void setApiToken(String apiToken) {
        mApiToken = apiToken;
    }

    public RegisterClient getClient() {
        return mRegisterClient;
    }

    public void setClient(RegisterClient registerClient) {
        mRegisterClient = registerClient;
    }

}
