package io.github.mpao.baking.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import io.github.mpao.baking.R;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Recipe;

public class RecipeActivity extends AppCompatActivity implements FragmentConnector{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
    }

    @Override
    public void onElementSelected(Recipe recipe, int position) {

        if (findViewById(R.id.step_container) != null) {
            // if app shows two panes layout, and fragment detail exists
            ContentFragment fragment = ContentFragment.newInstance(recipe, position);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.step_container, fragment);
            ft.commit();
        }else{
            // phone layout; use another activity as detail view
            Intent intent = new Intent(this, StepActivity.class);
            intent.putExtra(App.RECIPE_VALUE, recipe);
            intent.putExtra(App.STEP_INDEX, position);
            this.startActivity(intent);
        }

    }

}
