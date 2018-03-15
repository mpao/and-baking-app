package io.github.mpao.baking.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.github.mpao.baking.R;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.viewmodels.DetailViewModel;

public class StepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        StepFragment detail = (StepFragment)getSupportFragmentManager().findFragmentById(R.id.step_view);

        int position = getIntent().getIntExtra(App.STEP_INDEX, App.INVALID);
        int id   = getIntent().getIntExtra(App.RECIPE_VALUE, App.INVALID);

        // get data from viewmodel
        DetailViewModel viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        if(id != App.INVALID ){
            viewModel.get(id).observe(this, recipe ->{
                if(recipe != null) {
                    if(findViewById(R.id.detail_width_marker) != null) {
                        /*
                         * if the app is put in the landscape mode during the choice of a step,
                         * or the user turns the device while StepActivity is on screen the responsability
                         * returns to DetailActivity, and the StepActivity is as if it had never existed
                         * (no history flag). View the detail_width_marker description in the
                         * activity_step.xml alternative layout
                         */
                        Intent intent = new Intent( this, DetailActivity.class );
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.putExtra(App.RECIPE_VALUE, recipe.getId());
                        intent.putExtra(App.STEP_INDEX, position);
                        this.startActivity(intent);
                        this.finish();
                    }else{
                        detail.setUpDetailElement(recipe, position);
                    }
                }
            });
        }

    }

}
