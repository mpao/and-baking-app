package io.github.mpao.baking.di;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import io.github.mpao.baking.models.repositories.RecipeData;
import io.github.mpao.baking.models.repositories.RecipeDataImpl;

/**
 * Inject the right repository for data request
 */
@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public RecipeData provides(){
        return new RecipeDataImpl();
    }

}
