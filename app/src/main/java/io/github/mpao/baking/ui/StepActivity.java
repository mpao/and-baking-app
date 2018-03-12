package io.github.mpao.baking.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.github.mpao.baking.R;

public class StepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
    }

    /*
     * if the app is put in the landscape mode during the choice of a step,
     * or the user turns the device while StepActivity is on screen the responsability
     * returns to DetailActivity, and the StepActivity is as if it had never existed
     * (no history flag). View the detail_width_marker description in the
     * activity_step.xml alternative layout
     */
    @Override
    protected void onResume() {

        super.onResume();
        if(findViewById(R.id.detail_width_marker) != null) {
            Intent intent = new Intent( this, DetailActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            this.startActivity(intent);
            this.finish();
        }

    }

}
