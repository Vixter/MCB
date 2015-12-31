package ru.winfected.mcb.ui.themoviedb;

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
import ru.winfected.mcb.R;
import ru.winfected.mcb.model.themoviedb.ListMovie;
import ru.winfected.mcb.network.themoviedb.MovieConfig;
import ru.winfected.mcb.network.themoviedb.MoviesRestRequest;

/**
 * Created by winfe on 31.12.2015.
 */
public class MovieDiscoverFragment extends Fragment implements Callback<ListMovie> {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comics, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        MovieConfig config = new MovieConfig(getString(R.string.themoviedb_api_key));
        MoviesRestRequest restRequest = config.getRetrofit().create(MoviesRestRequest.class);
        restRequest.getAllMovies().enqueue(this);
        //restRequest.getAllPopularMovies("popularity.desc").enqueue(this);
        return view;
    }

    @Override
    public void onResponse(Response<ListMovie> response, Retrofit retrofit) {
        if(response.body() == null)
            Toast.makeText(getContext(), response.message() + " " + String.valueOf(response.code()), Toast.LENGTH_LONG).show();
        else recyclerView.setAdapter(new MovieAdapter(new ArrayList(response.body().getResults())));
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

}
