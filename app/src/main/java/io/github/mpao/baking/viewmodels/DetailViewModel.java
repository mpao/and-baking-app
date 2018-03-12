package io.github.mpao.baking.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import javax.inject.Inject;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.models.repositories.RecipeData;

public class DetailViewModel extends ViewModel {

    @Inject RecipeData repo;

    public DetailViewModel(){
        App.graph.inject(this);
    }

    public LiveData<Recipe> get(int id) {
        return repo.get(id);
    }

}
