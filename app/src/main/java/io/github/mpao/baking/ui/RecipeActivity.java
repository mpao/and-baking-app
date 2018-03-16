package io.github.mpao.baking.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import io.github.mpao.baking.R;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Recipe;

public class RecipeActivity extends AppCompatActivity implements FragmentConnector{

    private Recipe recipe;
    private int position;
    private boolean hasItemSelected = false;
    private static final String TAG = "save";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        /*
         * if there was a selection in landscape mode, get the information
         * about it, and launch an intent to the StepActivity ( onElementSelected
         * in portrait modo )
         */
        if(savedInstanceState != null ){
            if(savedInstanceState.getBoolean(TAG)) {
                this.onElementSelected(
                        savedInstanceState.getParcelable(App.RECIPE_VALUE),
                        savedInstanceState.getInt(App.STEP_INDEX));
            }
        }
        /*
         * From a portrait StepActivity to a landscape RecipeActivity
         */
        if(getIntent() != null && getIntent().getBooleanExtra("FromStep", false)) {
            Recipe recipe = getIntent().getParcelableExtra(App.RECIPE_VALUE);
            int position = getIntent().getIntExtra(App.STEP_INDEX, App.INVALID);
            getIntent().putExtra("FromStep", false); // override the value if exists
            this.onElementSelected(recipe, position);
        }

    }

    /**
     * Interface method implementation for master-detail layout. It's executed when
     * the user tap on an element of the list
     * @see io.github.mpao.baking.ui.adapters.StepsAdapter.ViewHolder#bind
     * https://developer.android.com/training/basics/fragments/communicating.html
     * @param recipe shared element within fragments
     * @param position step index
     */
    @Override
    public void onElementSelected(Recipe recipe, int position) {

        if (findViewById(R.id.step_container) != null) {
            // if app shows two panes layout and I clicked on a list item
            // save the information about the selection, and open the
            // detail in the other fragment
            this.recipe = recipe;
            this.position = position;
            this.hasItemSelected = true;
            Fragment content = ContentFragment.newInstance(recipe, position);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.step_container, content);
            ft.commit();
        }else{
            // phone layout; use another activity as detail view
            Intent intent = new Intent(this, StepActivity.class);
            intent.putExtra(App.RECIPE_VALUE, recipe);
            intent.putExtra(App.STEP_INDEX, position);
            this.startActivity(intent);
        }

    }

    /*
     * save information about the item selection
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putParcelable(App.RECIPE_VALUE, recipe);
        outState.putInt(App.STEP_INDEX, position);
        outState.putBoolean(TAG, hasItemSelected);

    }

}
