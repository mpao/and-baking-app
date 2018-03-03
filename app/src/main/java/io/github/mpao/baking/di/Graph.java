package io.github.mpao.baking.di;

import javax.inject.Singleton;
import dagger.Component;
import io.github.mpao.baking.models.repositories.RecipeDataImpl;
import io.github.mpao.baking.viewmodels.ListViewModel;

/**
 * Dagger graph
 */
@Singleton
@Component(modules = {
        ContextModule.class,
        RetrofitModule.class,
        RepositoryModule.class,
        DatabaseModule.class
})

public interface Graph {

    void inject(RecipeDataImpl recipeData);
    void inject(ListViewModel listViewModel);

}
