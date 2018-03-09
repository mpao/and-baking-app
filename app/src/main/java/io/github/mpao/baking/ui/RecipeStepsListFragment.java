package io.github.mpao.baking.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import io.github.mpao.baking.R;
import io.github.mpao.baking.databinding.FragmentRecipeStepsBinding;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.ui.adapters.StepsAdapter;

public class RecipeStepsListFragment extends Fragment {

    private FragmentRecipeStepsBinding binding;
    private Recipe recipe;

    public RecipeStepsListFragment() {}

    public static Fragment newInstance(Recipe recipe){

        Fragment fragment = new RecipeStepsListFragment();
        Bundle args = new Bundle();
        args.putParcelable("recipe", recipe);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_steps, container, false);
        recipe = getArguments().getParcelable("recipe");
        CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        binding.list.setLayoutManager(layoutManager);
        binding.list.setHasFixedSize(true);
        binding.list.setAdapter( new StepsAdapter(recipe.getSteps()));
        binding.list.addOnScrollListener(new CenterScrollListener());

        return binding.getRoot();

    }

}
