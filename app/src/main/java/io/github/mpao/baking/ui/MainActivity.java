package io.github.mpao.baking.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import io.github.mpao.baking.R;
import io.github.mpao.baking.databinding.ActivityMainBinding;
import io.github.mpao.baking.ui.adapters.ListAdapter;
import io.github.mpao.baking.viewmodels.MainViewModel;

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
        RecyclerView.LayoutManager lm = new LinearLayoutManager( this );
        binding.list.setLayoutManager(lm);
        binding.list.setHasFixedSize(true);

    }

    /*
     * observe and update data from the model
     */
    private void observeData(MainViewModel viewModel){

        viewModel.getData().observe(this, list ->{
            ListAdapter adapter = new ListAdapter( list );
            binding.list.setAdapter(adapter);
        });

    }

}
