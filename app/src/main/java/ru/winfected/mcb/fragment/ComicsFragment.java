package ru.winfected.mcb.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;

import retrofit.Call;
import retrofit.Response;
import ru.winfected.mcb.R;
import ru.winfected.mcb.adapter.RecyclerViewAdapter;
import ru.winfected.mcb.model.*;
import ru.winfected.mcb.rest.Config;
import ru.winfected.mcb.rest.RestRequest;

/**
 * Created by winfe on 27.12.2015.
 */
public class ComicsFragment extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comics, container, false);

        Config config = new Config(getString(R.string.public_key), getString(R.string.private_key));
        RestRequest request = config.getRetrofit().create(RestRequest.class);
        Call<MarvelResponse<Characters>> call = request.getCharacters();
        ArrayList<ru.winfected.mcb.model.Character> characters;
        try {
            Response<MarvelResponse<Characters>> response = call.execute();
            characters = new ArrayList<>(response.body().getResponse().getCharacters());
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            recyclerView.setAdapter(new RecyclerViewAdapter(characters));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }

}
