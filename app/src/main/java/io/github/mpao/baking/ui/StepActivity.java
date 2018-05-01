package io.github.mpao.baking.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import io.github.mpao.baking.R;

public class StepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        if(findViewById(R.id.step_width_marker) != null){
            // Step activity in landscape mode ( see activity_step.xml for wXXXdp )
            // close this activity and pass controll to RecipeActivity
            finish();
        }

    }

    /**
     * Up navigation: behavior is the same of the back navigation
     * @return boolean
     */
    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }

}
