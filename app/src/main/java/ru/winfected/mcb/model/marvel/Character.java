package ru.winfected.mcb.model.marvel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by winfe on 27.12.2015.
 */
public class Character {

    @SerializedName("id") private int id;
    @SerializedName("name") private String name;
    @SerializedName("description") private String description;
    @SerializedName("modified") private String modified;
    @SerializedName("resourceURI") private String resourceURI;
    @SerializedName("thumbnail") private Image thumbnail;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getModified() {
        return modified;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

}
