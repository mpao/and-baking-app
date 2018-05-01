package io.github.mpao.baking.ui.widgets;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Just for collections and widgets, @see RemoteViewsFactory
 */
public class RecipeViewsService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeViewsFactory(this.getApplicationContext(), intent);
    }

}
