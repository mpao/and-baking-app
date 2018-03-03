package io.github.mpao.baking.di;

import android.app.Application;
import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Inject application context
 */
@Module
public class ContextModule {

    private Context context;

    public ContextModule(Application app){
        this.context = app.getBaseContext();
    }

    @Singleton
    @Provides
    public Context provides(){
        return context;
    }

}
