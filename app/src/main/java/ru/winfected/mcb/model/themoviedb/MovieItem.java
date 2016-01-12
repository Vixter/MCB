package ru.winfected.mcb.model.themoviedb;

import com.google.gson.annotations.SerializedName;

/**
 * Created by winfe on 31.12.2015.
 */
public class MovieItem
{
    @SerializedName("id") private String id;
    @SerializedName("title") private String title;
    @SerializedName("budget") private String budget;
    @SerializedName("poster_path") private String poster_path;
    @SerializedName("backdrop_path") private String backdrop_path;
    @SerializedName("vote_average") private String vote_average;
    @SerializedName("status") private String status;
    @SerializedName("runtime") private String runtime;
    @SerializedName("adult") private Boolean adult;
    @SerializedName("overview") private String overview;
    @SerializedName("release_date") private String release_date;
    @SerializedName("original_title") private String original_title;
    @SerializedName("popularity") private String popularity;

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString()
    {
        return "MovieItem [budget = "+budget+", vote_average = "+vote_average+", status = "+status+", runtime = "+runtime+
                ", adult = "+adult+", id = "+id+", title = "+title+", overview = "+overview+", release_date = "+release_date+
                ", original_title = "+original_title+", poster_path = "+poster_path+", popularity = "+popularity+"]";
    }
}
