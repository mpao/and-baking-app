package io.github.mpao.baking.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.mpao.baking.R;
import io.github.mpao.baking.entities.Recipe;

public class RecipeDescriptionFragment extends Fragment {

    public RecipeDescriptionFragment() {}

    public static Fragment newInstance(Recipe recipe){

        Fragment fragment = new RecipeDescriptionFragment();
        Bundle args = new Bundle();
        args.putParcelable("recipe", recipe);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //todo, description and ingredients list
        return inflater.inflate(R.layout.fragment_recipe_description, container, false);

    }

}
