package io.github.mpao.baking.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import io.github.mpao.baking.R;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Recipe;

public class StepActivity extends AppCompatActivity {

    ContentFragment content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        if (savedInstanceState == null) {
            // Insert detail fragment based on the item passed
            Recipe recipe = getIntent().getParcelableExtra(App.RECIPE_VALUE);
            int position  = getIntent().getIntExtra(App.STEP_INDEX, App.INVALID);
            content = ContentFragment.newInstance(recipe, position);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.step_container, content);
            ft.commit();
        }

    }

}
