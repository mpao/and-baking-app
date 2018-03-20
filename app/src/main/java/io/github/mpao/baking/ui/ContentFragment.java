package io.github.mpao.baking.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    private Activity activity;
    private Recipe recipe;
    private int position;

    /*
     * check the @Nullable value returned by getActivity()
     * getActivity() in Fragment got the @Nullable annotation with support libraries 27.0.0
     * as reported here https://goo.gl/hQ7fPQ
     */
    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        if (context instanceof Activity) {
            this.activity = (Activity) context;
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding  = DataBindingUtil.inflate(inflater, R.layout.fragment_content, container, false);
        // fragment created in portrait mode
        Intent intent = activity.getIntent();
        if( intent != null ){
            Recipe recipe = intent.getParcelableExtra(App.RECIPE_VALUE);
            int position  = intent.getIntExtra(App.STEP_INDEX, App.INVALID);
            update(recipe, position);
        }

        return binding.getRoot();

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            update(
                    savedInstanceState.getParcelable(App.RECIPE_VALUE),
                    savedInstanceState.getInt(App.STEP_INDEX)
            );
        }
    }

    @Override
    public void onDetach() {

        super.onDetach();
        this.activity = null;

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putParcelable(App.RECIPE_VALUE, recipe);
        outState.putInt(App.STEP_INDEX, position);

    }

    /*
     * Set the value for the Fragment model, and show it in the view
     */
    public void update(Recipe recipe, int position){

        if(position != App.INVALID && recipe != null) {
            this.position = position;
            this.recipe = recipe;
            Step step = recipe.getSteps().get(position);
            binding.setStep(step);
        }

    }

}
