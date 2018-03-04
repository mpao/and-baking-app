package io.github.mpao.baking.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.mpao.baking.R;
import io.github.mpao.baking.databinding.FragmentDetailBinding;
import io.github.mpao.baking.entities.Recipe;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        Recipe recipe = getActivity().getIntent().getParcelableExtra("element");
        this.setUpDetailElement(recipe);
        binding.executePendingBindings();
        return binding.getRoot();

    }

    /*
     * Bind the Recipe to the layout. It's usefull for the container activity
     * to update the fragment layout on screen rotation
     */
    protected void setUpDetailElement(Recipe recipe){
        binding.setRecipe(recipe);
    }

    /*
     * Check if the fragment is still binded to the layout. It's usefull for the container activity
     * to update the fragment layout on screen rotation
     */
    protected boolean isBinded(){
        return binding != null;
    }

}
