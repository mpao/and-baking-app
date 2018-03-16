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

public class StepActivity extends AppCompatActivity {

    private Recipe recipe;
    private int position;
    private Fragment content;
    private static final String TAG = "step";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        if (savedInstanceState == null) {
            // Create a new instance of the fragment with the intent information
            recipe = getIntent().getParcelableExtra(App.RECIPE_VALUE);
            position  = getIntent().getIntExtra(App.STEP_INDEX, App.INVALID);
            content = ContentFragment.newInstance(recipe, position);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.step_container, content);
            ft.commit();
        }else{
            // restore the previous fragment
            recipe = savedInstanceState.getParcelable(App.RECIPE_VALUE);
            position = savedInstanceState.getInt(App.STEP_INDEX);
            content = getSupportFragmentManager().getFragment(savedInstanceState, TAG);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.step_container, content);
            ft.commit();
        }

        if(findViewById(R.id.step_width_marker) != null){
            // Step activity in landscape mode ( see activity_step.xml for wXXXdp )
            // close this activity and pass controll to RecipeActivity
            Intent intent = new Intent(this, RecipeActivity.class);
            intent.putExtra(App.RECIPE_VALUE, recipe );
            intent.putExtra(App.STEP_INDEX, position);
            intent.putExtra("FromStep", true);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }

    }

    /*
     * save the fragment state
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(App.RECIPE_VALUE, recipe);
        outState.putInt(App.STEP_INDEX, position);
        getSupportFragmentManager().putFragment(outState, TAG, content);
    }

}
