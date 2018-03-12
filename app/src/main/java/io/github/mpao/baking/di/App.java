package io.github.mpao.baking.di;

import android.app.Application;

/**
 * Application class for creating the dagger graph
 * App class contain also a reference to an ID if a recipe
 * is selected. This is usefull for the navigation between
 * activities and fragments, getting the recipe informations
 * from the database with the ID.
 */
public class App extends Application {

    public static Graph graph;
    public static int recipeId;
    public static final int DEFAULT_ID = -1;

    @Override
    public void onCreate(){

        super.onCreate();
        graph = DaggerGraph.builder()
                .retrofitModule( new RetrofitModule() )
                .databaseModule( new DatabaseModule(this))
                .build();

    }

}
