package io.github.mpao.baking.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.mpao.baking.R;
import io.github.mpao.baking.databinding.FragmentListBinding;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.ui.adapters.StepsAdapter;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        Recipe recipe = getActivity().getIntent().getParcelableExtra(App.RECIPE_VALUE);

        this.setUp(recipe);
        return binding.getRoot();

    }

    /*
     * Set up the recipe information from the parent activity and inizialize
     * the recyclerview for the list of steps
     */
    protected void setUp(Recipe recipe){

        // set up the recyclerview
        RecyclerView.LayoutManager lm = new LinearLayoutManager( getActivity() );
        binding.list.setLayoutManager(lm);
        binding.list.setHasFixedSize(true);
        StepsAdapter adapter = new StepsAdapter(recipe, (FragmentConnector) getActivity());
        binding.list.setAdapter(adapter);

    }

}
