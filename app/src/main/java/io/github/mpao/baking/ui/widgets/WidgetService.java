package io.github.mpao.baking.ui.widgets;

import android.arch.lifecycle.LifecycleService;
import android.content.Intent;
import javax.inject.Inject;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.models.database.AppDatabase;
import io.github.mpao.baking.viewmodels.MainViewModel;

/**
 * Get the recipe from room database. The broadcast receiver for the service is the widget provider
 */
public class WidgetService extends LifecycleService {

    @Inject AppDatabase database;
    private static final int DEFAULT_VALUE = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        App.graph.inject(this);
    }

    @Override
    public void onStart(Intent intent, int startId) {

        super.onStart(intent, startId);
        int id     = intent.getIntExtra("id", DEFAULT_VALUE);
        int widget = intent.getIntExtra("widget", DEFAULT_VALUE);
        if(id != DEFAULT_VALUE) {
            MainViewModel viewModel = new MainViewModel();
            viewModel.init();
            viewModel.get(id).observe(this, recipe -> {
                Intent local = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
                local.putExtra("recipe", recipe);
                local.putExtra("widget", widget);
                this.sendBroadcast(local);
            });
        }

    }

}
