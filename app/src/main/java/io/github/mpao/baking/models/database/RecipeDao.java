package io.github.mpao.baking.models.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;
import io.github.mpao.baking.entities.Recipe;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Room implementation for insert and select all the recipes. No need for
 * a single selection, neither the delete
 */
@Dao
public interface RecipeDao {

    // keep in mind that the table name ( recipes ) is defined in Recipe.class
    @Query("SELECT * FROM recipes")
    LiveData<List<Recipe>> getAll();

    // overwrite object by ID, if there will be an update in the json, i will save it
    @Insert(onConflict = REPLACE)
    void insertAll(Recipe... recipes);

}

