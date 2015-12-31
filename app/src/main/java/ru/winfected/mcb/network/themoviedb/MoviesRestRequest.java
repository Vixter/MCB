package ru.winfected.mcb.network.themoviedb;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import ru.winfected.mcb.model.themoviedb.ListMovie;

/**
 * Created by winfe on 29.12.2015.
 */
public interface MoviesRestRequest {

    @GET("/3/discover/movie")
    Call<ListMovie> getAllMovies(@Query(Params.PARAM_RELEASE_DATE_gte) String date_gte,
                                 @Query(Params.PARAM_RELEASE_DATE_lte) String date_lte);

    @GET("/3/discover/movie")
    Call<ListMovie> getAllPopularMovies(@Query(Params.PARAM_SORT) String sort_by);
}
