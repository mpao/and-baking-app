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
import io.github.mpao.baking.databinding.FragmentDetailBinding;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.ui.adapters.StepsAdapter;

/*
 * Fragment for listing the step of a recipe preparation
 */
public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
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

    /*
     * retain this fragment when activity is re-initialized
     * otherwise binding field and recipe shall be null
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

}
