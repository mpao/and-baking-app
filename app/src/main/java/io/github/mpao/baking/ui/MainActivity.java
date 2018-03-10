package io.github.mpao.baking.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import io.github.mpao.baking.R;
import io.github.mpao.baking.databinding.ActivityMainBinding;
import io.github.mpao.baking.ui.adapters.RecipesAdapter;
import io.github.mpao.baking.viewmodels.MainViewModel;

/*
 * Proof of concept of CarouselLayoutManager. It's very nice, but
 * not for this use case.
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme); // see splash_screen.xml
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // get data from viewmodel
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        if( savedInstanceState == null) {
            viewModel.init();
        }
        this.observeData(viewModel);

        // set up the RecyclerView
        CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        binding.list.setLayoutManager(layoutManager);
        binding.list.setHasFixedSize(true);
        binding.list.addOnScrollListener(new CenterScrollListener());

    }

    /*
     * observe and update data from the model
     */
    private void observeData(MainViewModel viewModel){

        viewModel.getData().observe(this, list ->{
            RecipesAdapter adapter = new RecipesAdapter( list );
            binding.list.setAdapter(adapter);
        });

    }

}
