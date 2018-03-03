package io.github.mpao.baking.models.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import io.github.mpao.baking.entities.Recipe;

/**
 * Implement Room database for saving the recipes
 */
@Database(entities = {Recipe.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract RecipeDao recipeDao();

}
