package io.github.mpao.baking.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.github.mpao.baking.R;
import io.github.mpao.baking.entities.Recipe;

/*
 *
 */
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

    }

    /*
     * if the app is put in the landscape mode during the choice of the recipe,
     * or the user turns the device while DetailActivity is on screen
     * the intent from main activity is still caught but, instead of DetailFragment, the
     * responsability returns to MainActivity, and the DetailActivity is as if it had never existed
     * (no history flag). MainActivity in landscape catch this new intent in onCreate and
     * show the data in the two-panes layout
     */
    @Override
    protected void onResume() {

        super.onResume();

        @Nullable Recipe recipe = this.getIntent().getParcelableExtra("element");
        if(findViewById(R.id.detail_w720_marker) != null) {
            Intent intent = new Intent( this, MainActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.putExtra("element", recipe);
            this.startActivity(intent); //
            this.finish();
        }

    }

}
