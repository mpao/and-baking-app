package io.github.mpao.baking.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.mpao.baking.R;
import io.github.mpao.baking.databinding.FragmentListBinding;
import io.github.mpao.baking.ui.adapters.ListAdapter;
import io.github.mpao.baking.viewmodels.ListViewModel;

/**
 * Manage the views rappresenting the list of objects
 */
public class ListFragment extends Fragment {

    ListViewModel viewModel;
    FragmentListBinding binding;

    public ListFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);

        // get data from viewmodel
        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        if( savedInstanceState == null) {
            viewModel.init();
        }
        this.observeData(viewModel);

        RecyclerView.LayoutManager lm = new LinearLayoutManager( getActivity() );
        binding.list.setLayoutManager(lm);
        binding.list.setHasFixedSize(true);

        return binding.getRoot();

    }

    /*
     * retain this fragment when activity is re-initialized
     * otherwise binding and viewmodel field shall be null
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    private void observeData(ListViewModel viewModel){

        viewModel.getData().observe(this, list ->{
            ListAdapter adapter = new ListAdapter( list, (FragmentConnector) getActivity() );
            binding.list.setAdapter(adapter);
        });

    }

}
