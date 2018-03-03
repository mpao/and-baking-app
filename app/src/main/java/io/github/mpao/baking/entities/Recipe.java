package io.github.mpao.baking.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import io.github.mpao.baking.models.database.DataConverter;

/**
 * This is the main entity class for application and database. All the data are saved in the "recipes" table,
 * accessors are necessary for Room. The List attributes need a TypeConverters for saving in Room
 * @see DataConverter
 * The ID attribute is the primary key of the object
 */
@Entity(tableName = "recipes")
public class Recipe {

    //region Fields
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ingredients")
    @Expose
    @TypeConverters(DataConverter.class)
    private List<Ingredient> ingredients;
    @SerializedName("steps")
    @Expose
    @TypeConverters(DataConverter.class)
    private List<Step> steps;
    @SerializedName("servings")
    @Expose
    private Integer servings;
    @SerializedName("image")
    @Expose
    private String image;
    //endregion

    //region Getters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public Integer getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
    //endregion

    //region Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public void setImage(String image) {
        this.image = image;
    }
    //endregion

}
