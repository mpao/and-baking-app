package io.github.mpao.baking.models.network;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import io.github.mpao.baking.entities.Recipe;

/**
 * Create an array of object from the API json file
 */
public class RecipeDeserializer implements JsonDeserializer<Recipe[]> {

    @Override
    public Recipe[] deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {

        JsonElement data = json.getAsJsonArray();
        return new Gson().fromJson(data, type);

    }

}
