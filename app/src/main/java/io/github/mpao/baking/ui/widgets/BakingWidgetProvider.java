package io.github.mpao.baking.ui.widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RemoteViews;
import io.github.mpao.baking.R;
import io.github.mpao.baking.entities.Recipe;
import static android.content.Context.MODE_PRIVATE;

public class BakingWidgetProvider extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {

            setUp(context, appWidgetId);

        }

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Recipe recipe = intent.getParcelableExtra("recipe");
        int widgetid  = intent.getIntExtra("widget", -1);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
        if( recipe != null ) {
            // RecipeViewsFactory doesnt accept parcelable :O
            // It works only with bundles
            // https://stackoverflow.com/a/49464628/1588252
            Intent i = new Intent(context, RecipeViewsService.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("recipe", recipe);
            i.putExtra("bundle", bundle);

            views.setTextViewText(R.id.name, recipe.getName());
            views.setRemoteAdapter(R.id.list, i);
        }
        AppWidgetManager.getInstance(context).updateAppWidget(widgetid, views);

    }

    /*
     * This method is executed also when a new widget is about to be created
     */
    protected void setUp(Context context, int widgetId){

        SharedPreferences widgets = context.getSharedPreferences(context.getString(R.string.widgets), MODE_PRIVATE);
        int id = widgets.getInt( String.valueOf(widgetId), 0);

        Intent intent = new Intent(context, WidgetService.class);
        intent.putExtra("id", id);
        intent.putExtra("widget", widgetId);
        context.startService(intent);

    }

}
