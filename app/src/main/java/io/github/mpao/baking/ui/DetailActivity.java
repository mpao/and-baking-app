package io.github.mpao.baking.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.github.mpao.baking.R;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Step;
import io.github.mpao.baking.viewmodels.DetailViewModel;

/*
 * Show the information for a Recipe
 */
public class DetailActivity extends AppCompatActivity implements FragmentConnector{

    private DetailFragment list;
    private StepFragment detail;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        App.graph.inject(this);
        setContentView(R.layout.activity_detail);
        list   = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
        detail = (StepFragment)getSupportFragmentManager().findFragmentById(R.id.step_fragment);
        id     = getIntentValue();

    }

    /*
     * onResume will be called *after* onNewIntent()
     * and that's why, the viewmodel is defined here
     */
    @Override
    protected void onResume() {
        super.onResume();
        // get data from viewmodel
        DetailViewModel viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        if(id != App.INVALID ){
            viewModel.get(id).observe(this, recipe ->{
                if(recipe != null) {
                    list.setUp(recipe.getSteps());
                }
            });
        }
    }

    /*
     * This is a singleTop activity
     * if you navigate up to an activity on the current stack, the behavior is determined by the parent activity's launch mode.
     * If the parent activity has launch mode singleTop (or the up intent contains FLAG_ACTIVITY_CLEAR_TOP),
     * the parent is brought to the top of the stack, and its state is preserved.
     * The navigation intent is received by the parent activity's onNewIntent() method
     * https://developer.android.com/guide/topics/manifest/activity-element.html#lmode
     * https://developer.android.com/reference/android/app/Activity.html#onNewIntent(android.content.Intent)
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        id = getIntentValue();
    }

    /**
     * Interface method implementation for master-detail layout. It's executed when
     * the user tap on an element of the list
     * @see io.github.mpao.baking.ui.adapters.MainAdapter.ViewHolder#bind
     * https://developer.android.com/training/basics/fragments/communicating.html
     * @param step shared element within fragments
     */
    @Override
    public void onElementSelected(Step step) {

        if ( findViewById(R.id.step_fragment) != null && detail != null) {
            // if app shows two panes layout, and fragment detail exists
            detail.setUpDetailElement(step);
        }else{
            // phone layout; use another activity as detail view
            Intent intent = new Intent(this, StepActivity.class);
            intent.putExtra(App.STEP_INDEX, step);
            this.startActivity(intent);
        }

    }

    /*
     * Get the ID of the selected recipe from an upcoming intent
     */
    private int getIntentValue(){
        return getIntent().getIntExtra(App.RECIPE_VALUE, App.INVALID);
    }

}
