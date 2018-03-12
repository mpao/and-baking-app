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
import io.github.mpao.baking.databinding.FragmentStepBinding;
import io.github.mpao.baking.entities.Step;

public class StepFragment extends Fragment {

    private FragmentStepBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding  = DataBindingUtil.inflate(inflater, R.layout.fragment_step, container, false);
        Step step = getActivity().getIntent().getParcelableExtra("tmp"); //todo fixme

        this.setUpDetailElement(step);
        return binding.getRoot();

    }

    // todo: fix screen rotation. i'd like that the step screen were not lost
    /*
     * retain this fragment when activity is re-initialized
     * otherwise binding field shall be null and @code #setUpDetailElement
     * will crash
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    /*
     * Bind the Step to the layout. It's usefull for the container activity
     * to update the fragment layout on screen rotation
     */
    protected void setUpDetailElement(Step step){
        binding.setStep(step);
        binding.executePendingBindings();
    }

}
