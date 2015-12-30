package ru.winfected.mcb.ui.characters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import ru.winfected.mcb.R;
import ru.winfected.mcb.model.marvel.Characters;
import ru.winfected.mcb.model.marvel.MarvelResponse;
import ru.winfected.mcb.model.themoviedb.ListMovie;
import ru.winfected.mcb.model.themoviedb.Movie;
import ru.winfected.mcb.network.marvel.MarvelConfig;
import ru.winfected.mcb.network.marvel.MarvelRestRequest;
import ru.winfected.mcb.network.themoviedb.MovieConfig;
import ru.winfected.mcb.network.themoviedb.MoviesRestRequest;
import ru.winfected.mcb.ui.RecyclerViewAdapter;

/**
 * Created by winfe on 27.12.2015.
 */
public class CharactersFragment extends Fragment implements Callback<ListMovie> { //<MarvelResponse<Characters>>{

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comics, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        //MarvelConfig config = new MarvelConfig(getString(R.string.public_key), getString(R.string.private_key));
        //MarvelRestRequest request = config.getRetrofit().create(MarvelRestRequest.class);
        //request.getCharacters().enqueue(this);

        MovieConfig config = new MovieConfig(getString(R.string.themoviedb_api_key));
        MoviesRestRequest restRequest = config.getRetrofit().create(MoviesRestRequest.class);
        restRequest.getAllMovies().enqueue(this);

        return view;
    }

    /*@Override
    public void onResponse(Response<MarvelResponse<Characters>> response, Retrofit retrofit) {
        if(response.body() == null)
            Toast.makeText(getContext(),response.message()+" "+String.valueOf(response.code()), Toast.LENGTH_LONG).show();
        else recyclerView.setAdapter(new RecyclerViewAdapter(new ArrayList<>(response.body().getResponse().getCharacters())));
    }
    */

    @Override
    public void onResponse(Response<ListMovie> response, Retrofit retrofit) {
        if(response.body() == null)
            Toast.makeText(getContext(),response.message()+" "+String.valueOf(response.code()), Toast.LENGTH_LONG).show();
        else recyclerView.setAdapter(new RecyclerViewAdapter(new ArrayList(response.body().getResults())));
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }
}
