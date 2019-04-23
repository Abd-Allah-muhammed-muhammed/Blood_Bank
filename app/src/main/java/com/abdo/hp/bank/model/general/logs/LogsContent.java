
package com.abdo.hp.bank.model.general.logs;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class LogsContent {

    @SerializedName("api_token")
    private String mApiToken;
    @SerializedName("message")
    private String mMessage;

    public String getApiToken() {
        return mApiToken;
    }

    public void setApiToken(String apiToken) {
        mApiToken = apiToken;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
