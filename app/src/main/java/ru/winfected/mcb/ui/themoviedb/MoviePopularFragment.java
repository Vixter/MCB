package ru.winfected.mcb.ui.themoviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import ru.winfected.mcb.MCBApplication;
import ru.winfected.mcb.R;
import ru.winfected.mcb.db.MovieDatabaseHelper;
import ru.winfected.mcb.model.themoviedb.ListMovie;
import ru.winfected.mcb.model.themoviedb.Movie;
import ru.winfected.mcb.network.themoviedb.MovieConfig;
import ru.winfected.mcb.network.themoviedb.MoviesRestRequest;
import ru.winfected.mcb.ui.EndlessRecyclerViewScrollListener;

/**
 * Created by winfe on 31.12.2015.
 */
public class MoviePopularFragment extends Fragment implements Callback<ListMovie> {

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    MoviesRestRequest restRequest;
    MovieDatabaseHelper databaseHelper;
    boolean isActiveNetwork;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comics, container, false);
        MovieConfig config = new MovieConfig(getString(R.string.themoviedb_api_key));
        databaseHelper = MovieDatabaseHelper.getInstance(getContext());

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        restRequest = config.getRetrofit().create(MoviesRestRequest.class);
        movieAdapter = new MovieAdapter(null);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (isActiveNetwork){
                    restRequest.getAllPopularMovies(page + 1, "popularity.desc").enqueue(MoviePopularFragment.this);
                } else {
                    movieAdapter.addAll(new ArrayList<Movie>(databaseHelper.getMoviesByPopularity(page)));
                }
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(view.getContext(), new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(view.getContext(), MovieInformationActivity.class)
                        .putExtra("MovieID", movieAdapter.getMovieID(position))
                        .putExtra("MovieTitle", movieAdapter.getMovieTitle(position)));
            }

        }));

        isActiveNetwork = MCBApplication.isConnectingToInternet(view.getContext().getApplicationContext());

        if (isActiveNetwork){
            restRequest.getAllPopularMovies(1, "popularity.desc").enqueue(this);
        } else {
            movieAdapter.addAll(new ArrayList<Movie>(databaseHelper.getMoviesByPopularity(1)));
        }

        return view;
    }

    @Override
    public void onResponse(Response<ListMovie> response, Retrofit retrofit) {
        if(response.body() == null)
            Toast.makeText(getContext().getApplicationContext(), response.message() + " " + String.valueOf(response.code()), Toast.LENGTH_LONG).show();
        else {
            ListMovie listMovie = response.body();
            movieAdapter.addAll(listMovie.getResults());

            for(Movie m : listMovie.getResults()) databaseHelper.addMovie(m);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getContext().getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

}
