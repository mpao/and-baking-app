package io.github.mpao.baking.ui;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.github.mpao.baking.R;
import io.github.mpao.baking.databinding.ActivityRecipeBinding;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.ui.adapters.RecipePagerAdapter;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActivityRecipeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe);
        Recipe recipe = getIntent().getParcelableExtra("value");
        // Set up the ViewPager with the sections adapter. Swipe and tap enabled
        RecipePagerAdapter pager = new RecipePagerAdapter(getSupportFragmentManager(), recipe);
        binding.container.setAdapter(pager);
        binding.container.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
        binding.tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(binding.container));

    }

}
