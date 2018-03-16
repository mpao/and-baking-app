package io.github.mpao.baking.di;

import javax.inject.Singleton;
import dagger.Component;
import io.github.mpao.baking.models.repositories.RecipeDataImpl;
import io.github.mpao.baking.viewmodels.MainViewModel;

/**
 * Dagger graph
 */
@Singleton
@Component(modules = {
        RetrofitModule.class,
        RepositoryModule.class,
        DatabaseModule.class
})

public interface Graph {

    void inject(RecipeDataImpl recipeData);
    void inject(MainViewModel listViewModel);

}
