package io.github.mpao.baking.models.repositories;

import android.arch.lifecycle.LiveData;
import java.util.List;
import io.github.mpao.baking.entities.Recipe;

/**
 * Use interface for decoupling the repository implementation from the code.
 * Dagger module is where the app define the right class for implementation
 */
public interface RecipeData {

    LiveData<List<Recipe>> get();

}
