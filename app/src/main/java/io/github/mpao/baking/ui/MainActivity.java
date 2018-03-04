package io.github.mpao.baking.ui;

import android.content.Intent;
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

        if (fragment != null && fragment.isBinded()) {
            // the fragment exists and is correctly binded to the layout, then set up the views
            fragment.setUpDetailElement(recipe);
        }else{
            // phone layout; use another activity as detail view
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("element", recipe);
            this.startActivity(intent);
        }

    }

}
