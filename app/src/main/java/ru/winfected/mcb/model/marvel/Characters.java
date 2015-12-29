package ru.winfected.mcb.model.marvel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by winfe on 27.12.2015.
 */
public class Characters {
    @SerializedName("offset") private int offset;
    @SerializedName("limit") private int limit;
    @SerializedName("total") private int total;
    @SerializedName("count") private int count;
    @SerializedName("results") private List<Character> characters = new ArrayList<>();

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getTotal() {
        return total;
    }

    public int getCount() {
        return count;
    }

    public List<Character> getCharacters() {
        return characters;
    }

}
