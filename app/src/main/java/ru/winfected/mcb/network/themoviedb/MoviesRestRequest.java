package ru.winfected.mcb.network.themoviedb;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import ru.winfected.mcb.model.themoviedb.ListMovie;

/**
 * Created by winfe on 29.12.2015.
 */
public interface MoviesRestRequest {

    @GET("/3/discover/movie")
    Call<ListMovie> getAllMovies();

}
