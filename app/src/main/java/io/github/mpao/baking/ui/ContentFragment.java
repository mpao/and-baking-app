package io.github.mpao.baking.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.mpao.baking.R;
import io.github.mpao.baking.databinding.FragmentContentBinding;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.entities.Step;

public class ContentFragment extends Fragment {

    private FragmentContentBinding binding;
    private Step step;

    public static ContentFragment newInstance(Recipe recipe, int position){

        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putParcelable(App.RECIPE_VALUE, recipe);
        args.putInt(App.STEP_INDEX, position);
        fragment.setArguments(args);
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding  = DataBindingUtil.inflate(inflater, R.layout.fragment_content, container, false);
        Recipe recipe = (Recipe)getArguments().getParcelable(App.RECIPE_VALUE);
        step = recipe.getSteps().get(getArguments().getInt(App.STEP_INDEX));
        binding.setStep(step);
        return binding.getRoot();

    }

}
