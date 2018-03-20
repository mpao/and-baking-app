package io.github.mpao.baking.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import io.github.mpao.baking.R;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Recipe;

public class RecipeActivity extends AppCompatActivity implements FragmentConnector{

    private final String STATE = "hasItemSelected";
    private boolean hasItemSelected = false;
    private ContentFragment fragment;
    private Recipe recipe;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        fragment = (ContentFragment)getSupportFragmentManager().findFragmentById(R.id.step_fragment);

        // User selected an item from the list in _landscape_ mode, but now the device
        // is recreating the activity in portrait mode
        if(savedInstanceState != null && savedInstanceState.getBoolean(STATE) && fragment == null){
            this.onElementSelected(
                    savedInstanceState.getParcelable(App.RECIPE_VALUE),
                    savedInstanceState.getInt(App.STEP_INDEX)
            );
        }

        // User selected an item from the list in _portrait_ mode, but not whe device
        // is recreating the activity in landscape mode
        if(savedInstanceState != null && fragment != null){
            this.onElementSelected(
                    savedInstanceState.getParcelable(App.RECIPE_VALUE),
                    savedInstanceState.getInt(App.STEP_INDEX)
            );
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

        // save information for selection
        this.recipe   = recipe;
        this.position = position;

        if (fragment != null && fragment.isInLayout()) {
            // if app shows two panes layout and I clicked on a list item
            // open the detail in the other fragment
            fragment.update(recipe, position);
            hasItemSelected = true;
        }else{
            // phone layout; use another activity as detail view
            Intent intent = new Intent(this, StepActivity.class);
            intent.putExtra(App.RECIPE_VALUE, recipe);
            intent.putExtra(App.STEP_INDEX, position);
            this.startActivity(intent);
        }

    }

    /*
     * Save activity state
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE, hasItemSelected);        // user has choose an item
        outState.putParcelable(App.RECIPE_VALUE, recipe);   // the value of recipe
        outState.putInt(App.STEP_INDEX, position);          // the item choosen by the user
    }

}
