package ru.winfected.mcb.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by winfe on 27.12.2015.
 */
public class MarvelResponse<T> {

    @SerializedName("code") private int code;
    @SerializedName("status") private String status;
    @SerializedName("copyright") private String copyright;
    @SerializedName("attributionText") private String attributionText;
    @SerializedName("attributionHTML") private String getAttributionHtml;
    @SerializedName("etag") private String etag;
    @SerializedName("data") private T response;

    //region getters
    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public String getGetAttributionHtml() {
        return getAttributionHtml;
    }

    public String getEtag() {
        return etag;
    }

    public T getResponse() {
        return response;
    }
    //endregion
}
