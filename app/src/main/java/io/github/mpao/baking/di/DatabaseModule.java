package io.github.mpao.baking.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import io.github.mpao.baking.models.database.AppDatabase;

/**
 * Inject database as singleton
 */
@Module
public class DatabaseModule {

    private Context context;

    public DatabaseModule(Application app){
        this.context = app.getApplicationContext();
    }

    /*
     * No migration fallback, at the moment is pointless having
     * a migration/update policy. Destroy the db and recreate it
     */
    @Singleton
    @Provides
    public AppDatabase provides(){
        return Room
                .databaseBuilder(context,  AppDatabase.class, "app-db")
                .fallbackToDestructiveMigration()
                .build();
    }

}
