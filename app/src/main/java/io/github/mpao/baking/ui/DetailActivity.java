package io.github.mpao.baking.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import javax.inject.Inject;
import io.github.mpao.baking.R;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Step;
import io.github.mpao.baking.models.database.AppDatabase;
import io.github.mpao.baking.viewmodels.DetailViewModel;

/*
 * Show the information for a Recipe
 */
public class DetailActivity extends AppCompatActivity implements FragmentConnector{

    DetailFragment list;
    StepFragment detail;
    @Inject AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        App.graph.inject(this);
        setContentView(R.layout.activity_detail);
        list   = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
        detail = (StepFragment)getSupportFragmentManager().findFragmentById(R.id.step_fragment);

        // get data from viewmodel
        DetailViewModel viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        viewModel.get(App.recipeId).observe(this, recipe ->{
            if(recipe != null) {
                list.setUp(recipe.getSteps());
            }
        });

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
