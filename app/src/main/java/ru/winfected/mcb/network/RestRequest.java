package ru.winfected.mcb.network;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import ru.winfected.mcb.model.Characters;
import ru.winfected.mcb.model.MarvelResponse;

/**
 * Created by winfe on 27.12.2015.
 */
public interface RestRequest {

    @GET("characters")
    Call<MarvelResponse<Characters>> getCharacters();

    @GET("characters/{id}")
    Call<MarvelResponse<Characters>> getCharacter(@Path("id") String characterId);

}
