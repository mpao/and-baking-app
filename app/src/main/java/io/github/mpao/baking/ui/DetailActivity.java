package io.github.mpao.baking.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import javax.inject.Inject;
import io.github.mpao.baking.R;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.entities.Step;
import io.github.mpao.baking.models.database.AppDatabase;

/*
 * Show the information for a Recipe
 */
public class DetailActivity extends AppCompatActivity implements FragmentConnector{

    DetailFragment list;
    StepFragment detail;
    @Inject AppDatabase database;
    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        App.graph.inject(this);
        setContentView(R.layout.activity_detail);
        list   = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
        detail = (StepFragment)getSupportFragmentManager().findFragmentById(R.id.step_fragment);
        new AsyncTask<Void, Void, Void>() { //todo pass to MVVM
            @Override
            protected Void doInBackground(Void... voids) {
                recipe = database.recipeDao().getRecipe(App.recipeId);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                list.setUp(recipe.getSteps());
            }
        }.execute();

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
            intent.putExtra("tmp", step); // todo fixme
            this.startActivity(intent);
        }

    }

}
