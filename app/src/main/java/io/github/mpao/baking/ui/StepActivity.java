package io.github.mpao.baking.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.github.mpao.baking.R;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Recipe;

public class StepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
    }

    /*
     * if the app is put in the landscape mode during the choice of a step,
     * or the user turns the device while StepActivity is on screen
     * the intent from detail activity is still caught but, instead of StepFragment, the
     * responsability returns to DetailActivity, and the StepActivity is as if it had never existed
     * (no history flag). DetailActivity in landscape catch this new intent in onCreate and
     * show the data in the two-panes layout
     */
    @Override
    protected void onResume() {

        super.onResume();
        @Nullable Recipe recipe = this.getIntent().getParcelableExtra(App.INTENT_NAME);
        if(findViewById(R.id.step_fragment) != null) {
            Intent intent = new Intent( this, DetailActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.putExtra(App.INTENT_NAME, recipe);
            this.startActivity(intent);
            this.finish();
        }

    }

}
