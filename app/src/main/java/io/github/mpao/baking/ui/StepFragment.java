package io.github.mpao.baking.ui;

import android.app.Activity;
import android.content.Context;
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
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Recipe;

public class StepFragment extends Fragment {

    private FragmentStepBinding binding;
    @Nullable private Recipe recipe;
    private int position;
    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding  = DataBindingUtil.inflate(inflater, R.layout.fragment_step, container, false);
        // recipe is null only if the app is over w720dp
        recipe   = activity.getIntent().getParcelableExtra(App.INTENT_NAME);
        position = activity.getIntent().getIntExtra("position",0);

        this.setUpDetailElement(recipe, position);
        return binding.getRoot();

    }

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

    /*
     * If the app is in landscape mode and the user changes the value of the recipe,
     * when it's turned in portrait mode this method has the task to update the value
     * of the recived intent that bind the activities. The result is that in portrait mode
     * the value of the visualized recipe will be preserve.
     */
    @Override
    public void onDetach() {

        super.onDetach();
        activity.getIntent().putExtra(App.INTENT_NAME, recipe); // see above, update the value
        activity.getIntent().putExtra("position", position);
        this.activity = null;

    }

    /*
     * Bind the Step to the layout. It's usefull for the container activity
     * to update the fragment layout on screen rotation
     */
    protected void setUpDetailElement(Recipe recipe, int position){
        this.recipe   = recipe; // see onDetach
        this.position = position;
        binding.setStep(recipe.getSteps().get(position));
        binding.executePendingBindings();
    }

}
