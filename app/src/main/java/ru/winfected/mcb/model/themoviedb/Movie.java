package ru.winfected.mcb.model.themoviedb;

import com.google.gson.annotations.SerializedName;

/**
 * Created by winfe on 29.12.2015.
 */
public class Movie {

    @SerializedName("id") private String id;
    @SerializedName("title") private String title;
    @SerializedName("original_language") private String original_language;
    @SerializedName("vote_average") private String vote_average;
    @SerializedName("backdrop_path") private String backdrop_path;
    @SerializedName("adult") private String adult;
    @SerializedName("overview") private String overview;
    @SerializedName("genre_ids") private String[] genre_ids;
    @SerializedName("release_date") private String release_date;
    @SerializedName("original_title") private String original_title;
    @SerializedName("vote_count") private String vote_count;
    @SerializedName("poster_path") private String poster_path;
    @SerializedName("video") private String video;
    @SerializedName("popularity") private String popularity;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String[] getGenre_ids() {
        return genre_ids;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getVote_count() {
        return vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getVideo() {
        return video;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString()
    {
        return "Movie [vote_average = "+vote_average+", backdrop_path = "+backdrop_path+", adult = "+adult+", id = "+id+", title = "+title+", overview = "+overview+", original_language = "+original_language+", genre_ids = "+genre_ids+", release_date = "+release_date+", original_title = "+original_title+", vote_count = "+vote_count+", poster_path = "+poster_path+", video = "+video+", popularity = "+popularity+"]";
    }
}
