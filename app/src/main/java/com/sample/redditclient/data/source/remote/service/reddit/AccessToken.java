package com.sample.redditclient.data.source.remote.service.reddit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by osarapul on 16.11.2017.
 */

public class AccessToken {
    @SerializedName("access_token")
    public String accessToken;

    @SerializedName("token_type")
    public String tokenType;

    @SerializedName("device_id")
    public String deviceId;

    @SerializedName("expires_in")
    public String expiresIn;

    @SerializedName("scope")
    public String scope;
}
