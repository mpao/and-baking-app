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
import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import io.github.mpao.baking.R;
import io.github.mpao.baking.databinding.FragmentDetailBinding;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.ui.adapters.StepAdapter;

/*
 * Fragment rappresenting the detail of a list's object.
 */
public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private Activity activity;
    CarouselLayoutManager layoutManager;
    @Nullable Recipe recipe;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        // Recipe is null only if the app is over w720dp
        recipe = activity.getIntent().getParcelableExtra("element");
        this.setUpDetailElement(recipe);
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
        activity.getIntent().putExtra("element", recipe); // see above, update the value
        this.activity = null;


    }

    /*
     * Bind the Recipe to the layout. It's usefull for the container activity
     * to update the fragment layout on screen rotation
     */
    protected void setUpDetailElement(Recipe recipe){
        this.recipe = recipe; // see onDetach
        binding.setRecipe(recipe);

        layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        StepAdapter adapter = new StepAdapter( recipe.getSteps() );
        binding.steps.setLayoutManager(layoutManager);
        binding.steps.setHasFixedSize(true);
        binding.steps.setAdapter(adapter);
        binding.steps.addOnScrollListener(new CenterScrollListener());

        binding.executePendingBindings();
    }

}
