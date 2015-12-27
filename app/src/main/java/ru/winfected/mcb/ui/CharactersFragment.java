package ru.winfected.mcb.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import ru.winfected.mcb.MainActivity;
import ru.winfected.mcb.R;
import ru.winfected.mcb.model.*;
import ru.winfected.mcb.network.Config;
import ru.winfected.mcb.network.RestRequest;

/**
 * Created by winfe on 27.12.2015.
 */
public class CharactersFragment extends Fragment implements Callback<MarvelResponse<Characters>>{

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comics, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        Config config = new Config(getString(R.string.public_key), getString(R.string.private_key));
        RestRequest request = config.getRetrofit().create(RestRequest.class);
        request.getCharacters().enqueue(this);

        return view;
    }

    @Override
    public void onResponse(Response<MarvelResponse<Characters>> response, Retrofit retrofit) {
        if(response.body() == null)
            Toast.makeText(getContext(),response.message()+" "+String.valueOf(response.code()), Toast.LENGTH_LONG).show();
        else recyclerView.setAdapter(new RecyclerViewAdapter(new ArrayList<>(response.body().getResponse().getCharacters())));
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }
}
