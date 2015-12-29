package ru.winfected.mcb.network.marvel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import ru.winfected.mcb.model.marvel.Characters;
import ru.winfected.mcb.model.marvel.MarvelResponse;

/**
 * Created by winfe on 27.12.2015.
 */
public interface MarvelRestRequest {

    @GET("characters")
    Call<MarvelResponse<Characters>> getCharacters();

    @GET("characters/{id}")
    Call<MarvelResponse<Characters>> getCharacter(@Path("id") String characterId);

}
