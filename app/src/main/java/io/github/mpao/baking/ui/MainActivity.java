package io.github.mpao.baking.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.github.mpao.baking.R;
import io.github.mpao.baking.entities.Recipe;

public class MainActivity extends AppCompatActivity implements FragmentConnector {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme); // see splash_screen.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        @Nullable Recipe recipe = this.getIntent().getParcelableExtra("element");
        if(recipe != null){
            /* this piece of code will be execute only in this case:
             * let say that portrait mode has a single layout and landscape has the master-detail layout,
             * if the app have the Detail activity in portrait mode with a recipe selected,
             * when it's turned in landscape mode, app switches to two-pane-layout and the viewed
             * recipe will be saved and passed to the detail fragment */
            this.onElementSelected(recipe);
        }

    }

    /**
     * Interface method implementation for master-detail layout. It's executed when
     * the user tap on an element of the list
     * @see io.github.mpao.baking.ui.adapters.ListAdapter.ViewHolder#bind
     * https://developer.android.com/training/basics/fragments/communicating.html
     * @param recipe shared element within fragments
     */
    @Override
    public void onElementSelected(Recipe recipe) {

        // get the fragment detail if exists ( tablet layout true, else false)
        DetailFragment fragment = (DetailFragment)getSupportFragmentManager().findFragmentById(R.id.detail_fragment);

        if ( findViewById(R.id.detail_fragment) != null && fragment != null) {
            // if app shows two panes layout, and fragment detail exists
            fragment.setUpDetailElement(recipe);
        }else{
            // phone layout; use another activity as detail view
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("element", recipe);
            this.startActivity(intent);
        }

    }

}
