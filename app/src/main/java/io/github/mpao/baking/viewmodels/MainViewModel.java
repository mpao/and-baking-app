package io.github.mpao.baking.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import java.util.List;
import javax.inject.Inject;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.models.repositories.RecipeData;

/**
 * Manage the data for the MainActivity
 */
public class MainViewModel extends ViewModel {

    private LiveData<List<Recipe>> data;
    @Inject RecipeData repo;

    public void init()  {

        App.graph.inject(this);
        this.data = repo.get();

    }

    public LiveData<List<Recipe>> getData() {

        return this.data;

    }

    public LiveData<Recipe> get(int id){

        return repo.get(id);

    }

}
