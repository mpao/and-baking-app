package io.github.mpao.baking.ui.widgets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import io.github.mpao.baking.R;
import io.github.mpao.baking.entities.Ingredient;
import io.github.mpao.baking.entities.Recipe;

/**
 * See documentation
 * https://developer.android.com/guide/topics/appwidgets/#implementing_collections
 * Implement collections for widgets
 */
public class RecipeViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private Recipe recipe;

    public RecipeViewsFactory(Context context, Intent intent) {

        this.context = context;
        Bundle bundle = intent.getBundleExtra("bundle");
        this.recipe  = bundle.getParcelable("recipe");

    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews row = new RemoteViews(context.getPackageName(), R.layout.row_widget);
        Ingredient i = recipe.getIngredients().get(position);
        String s = i.getIngredient();
        row.setTextViewText(R.id.ingredient, s);
        return row;

    }

    @Override
    public int getCount() {
        return recipe.getIngredients().size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //region Interface methods not implemented
    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onDataSetChanged() {}

    @Override
    public void onCreate() {}

    @Override
    public void onDestroy() {}
    //endregion

}