package io.github.mpao.baking.models.network;

import io.github.mpao.baking.entities.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Query the online json file
 */
public interface Api {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<Recipe[]> get();

}
