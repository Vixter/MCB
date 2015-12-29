package ru.winfected.mcb.network.themoviedb;

import com.google.gson.annotations.SerializedName;

/**
 * Created by winfe on 29.12.2015.
*/
public class AccessTokenResponse
{
    @SerializedName("expires_at") private String expires_at;
    @SerializedName("success") private String success;
    @SerializedName("request_token") private String request_token;

    public String getExpires_at ()
    {
        return expires_at;
    }

    public String getSuccess ()
    {
        return success;
    }

    public String getRequest_token ()
    {
        return request_token;
    }

    @Override
    public String toString()
    {
        return "AccessTokenResponse [expires_at = "+expires_at+", success = "+success+", request_token = "+request_token+"]";
    }
}