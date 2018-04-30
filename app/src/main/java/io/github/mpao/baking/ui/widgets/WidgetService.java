package io.github.mpao.baking.ui.widgets;

import android.arch.lifecycle.LifecycleService;
import android.content.Intent;
import javax.inject.Inject;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.models.database.AppDatabase;
import io.github.mpao.baking.viewmodels.MainViewModel;

public class WidgetService extends LifecycleService {

    @Inject AppDatabase database;
    private static final int DEFAULT_VALUE = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        App.graph.inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int id = intent.getIntExtra("id", DEFAULT_VALUE);
        if(id != DEFAULT_VALUE) {
            MainViewModel viewModel = new MainViewModel();
            viewModel.init();
            viewModel.get(id).observe(this, recipe -> {
                Intent local = new Intent("io.github.mpao.baking.ui.widgets");
                local.putExtra("recipe", recipe);
                this.sendBroadcast(local);
            });
        }
        return super.onStartCommand(intent, flags, startId);
    }

}
