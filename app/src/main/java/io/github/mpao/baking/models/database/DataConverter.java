package io.github.mpao.baking.models.database;

import android.arch.persistence.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import io.github.mpao.baking.entities.Ingredient;
import io.github.mpao.baking.entities.Step;

/**
 * Convert complex data type to sqlite compatible ones
 * see https://developer.android.com/training/data-storage/room/referencing-data.html
 * see https://goo.gl/U8PzVM
 * @see io.github.mpao.baking.entities.Recipe
 */
public class DataConverter {

    /*
     * transform a List of Ingredient to a json string for saving it in the db
     */
    @TypeConverter
    public String fromIngredientsList(List<Ingredient> ingredients){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ingredient>>() {}.getType();
        return ingredients != null ? gson.toJson(ingredients, type) : null;
    }

    /*
     * retrive a json string and transform it in a list of Ingredient
     */
    @TypeConverter
    public List<Ingredient> toIngredientsList(String string){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ingredient>>() {}.getType();
        return string != null ? gson.fromJson(string, type) : null;
    }

    /*
     * transform a List of Step to a json string for saving it in the db
     */
    @TypeConverter
    public String fromStepsList(List<Step> steps){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Step>>() {}.getType();
        return steps != null ? gson.toJson(steps, type) : null;
    }

    /*
     * retrive a json string and transform it in a list of Step
     */
    @TypeConverter
    public List<Step> toStepsList(String string){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Step>>() {}.getType();
        return string != null ? gson.fromJson(string, type) : null;
    }

}
