package io.github.mpao.baking.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.github.mpao.baking.R;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Recipe;

public class DetailActivity extends AppCompatActivity implements FragmentConnector{

    @Nullable Recipe recipe;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

    }

    @Override
    protected void onResume() {

        super.onResume();
        recipe   = this.getIntent().getParcelableExtra(App.INTENT_NAME);
        position = this.getIntent().getIntExtra("position", 0);
        if(recipe != null && position != 0){
            /* this piece of code will be execute only in this case:
             * let say that portrait mode has a single layout and landscape has the master-detail layout,
             * if the app have the Detail activity in portrait mode with a recipe selected,
             * when it's turned in landscape mode, app switches to two-pane-layout and the viewed
             * recipe will be saved and passed to the detail fragment */
            this.onElementSelected(recipe, position);
        }

    }

    /**
     * Interface method implementation for master-detail layout. It's executed when
     * the user tap on an element of the list
     * @see io.github.mpao.baking.ui.adapters.MainAdapter.ViewHolder#bind
     * https://developer.android.com/training/basics/fragments/communicating.html
     * @param recipe shared element within fragments
     * @param position position of the step
     */
    @Override
    public void onElementSelected(Recipe recipe, int position) {

        // get the fragment detail if exists (tablet layout true, else false)
        StepFragment fragment = (StepFragment)getSupportFragmentManager().findFragmentById(R.id.step_fragment);

        if ( findViewById(R.id.step_fragment) != null && fragment != null) {
            // if app shows two panes layout, and fragment detail exists
            fragment.setUpDetailElement(recipe, position);
        }else{
            // phone layout; use another activity as detail view
            Intent intent = new Intent(this, StepActivity.class);
            intent.putExtra(App.INTENT_NAME, recipe);
            intent.putExtra("position", position);
            this.startActivity(intent);
        }

    }

}
