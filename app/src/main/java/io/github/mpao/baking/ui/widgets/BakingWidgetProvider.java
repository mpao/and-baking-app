package io.github.mpao.baking.ui.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import io.github.mpao.baking.R;
import io.github.mpao.baking.ui.MainActivity;

public class BakingWidgetProvider extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // loop for each widget
        for (int appWidgetId : appWidgetIds) {
            // Create intent to launch main activity
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
            views.setOnClickPendingIntent(R.id.name, pendingIntent);

            // update widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }

    }

}
