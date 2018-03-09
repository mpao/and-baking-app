package io.github.mpao.baking.models.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.models.database.AppDatabase;
import io.github.mpao.baking.models.network.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation for the data repository
 */
public class RecipeDataImpl implements RecipeData {

    @Inject Api api;
    @Inject AppDatabase database;
    final private MutableLiveData<List<Recipe>> data = new MutableLiveData<>();

    public RecipeDataImpl(){
        App.graph.inject(this);
    }

    /*
     * Get the data in a background thread with enqueue method
     */
    @Override
    public LiveData<List<Recipe>> get() {

        api.get().enqueue(new Callback<Recipe[]>() {
            @Override
            public void onResponse(@NonNull Call<Recipe[]> call, @NonNull Response<Recipe[]> response) {

                database.recipeDao().insertAll( response.body() );
                //data.setValue( Arrays.asList( response.body() ) );

            }

            @Override
            public void onFailure(@NonNull Call<Recipe[]> call, Throwable t) {
                Log.d("debug", "error :|");
            }
        });

        return database.recipeDao().getAll();

    }

}
