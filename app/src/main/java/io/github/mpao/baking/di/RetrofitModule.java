package io.github.mpao.baking.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executors;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.models.network.Api;
import io.github.mpao.baking.models.network.RecipeDeserializer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Inject the Api class for retrofit network requests
 */
@Module
public class RetrofitModule {

    private static final String API_URL = "https://d17h27t6h515a5.cloudfront.net/";

    /*
     * This method provides the Api for network request with a Retrofit object that has
     * the callback running outside the main thread. Just because on the onResponse I have to
     * access to the database for insert
     */
    @Singleton
    @Provides
    public Api provides(){

        Gson gson = new GsonBuilder().registerTypeAdapter(Recipe[].class, new RecipeDeserializer()).create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(Api.class);

    }

}
