package io.github.mpao.baking.di;

import android.app.Application;

/**
 * Application class for creating the dagger graph
 * App class contain also a constant value for define
 * a *not selected value*, and some string for labeling
 * intent.
 */
public class App extends Application {

    public static Graph graph;
    public static final int INVALID         = -1;
    public static final String RECIPE_VALUE = "recipe";
    public static final String STEP_INDEX   = "index";

    @Override
    public void onCreate(){

        super.onCreate();
        graph = DaggerGraph.builder()
                .retrofitModule( new RetrofitModule() )
                .databaseModule( new DatabaseModule(this))
                .build();

    }

}
