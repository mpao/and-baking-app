package io.github.mpao.baking.di;

import android.app.Application;

/**
 * Application class for creating the dagger graph
 */
public class App extends Application {

    public static Graph graph;

    @Override
    public void onCreate(){

        super.onCreate();
        graph = DaggerGraph.builder()
                .contextModule( new ContextModule(this) )
                .retrofitModule( new RetrofitModule() )
                .build();

    }

}
