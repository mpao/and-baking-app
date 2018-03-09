package io.github.mpao.baking.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.ui.RecipeDescriptionFragment;
import io.github.mpao.baking.ui.RecipeStepsListFragment;

public class RecipePagerAdapter extends FragmentPagerAdapter {

    private Recipe recipe;

    public RecipePagerAdapter(FragmentManager fm, Recipe recipe) {
        super(fm);
        this.recipe = recipe;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position){
            case 0: fragment = RecipeDescriptionFragment.newInstance(recipe); break;
            case 1: fragment = RecipeStepsListFragment.newInstance(recipe); break;
        }
        return fragment;

    }

    @Override
    public int getCount() {
        return 2;
    }

}
