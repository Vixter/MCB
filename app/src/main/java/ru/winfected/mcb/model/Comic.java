package ru.winfected.mcb.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by winfe on 27.12.2015.
 */
public class Comic {

    @SerializedName("id") private int id;
    @SerializedName("digitalId") private int digitalId;
    @SerializedName("title") private String title;
    @SerializedName("description") private String description;
    @SerializedName("isbn") private String isbn;
    @SerializedName("format") private String format;
    @SerializedName("series") private String series;

    public int getId() {
        return id;
    }

    public int getDigitalId() {
        return digitalId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getFormat() {
        return format;
    }

    public String getSeries() {
        return series;
    }

}
