package io.github.mpao.baking.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import io.github.mpao.baking.R;
import io.github.mpao.baking.databinding.FragmentListBinding;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.models.MockData;
import io.github.mpao.baking.ui.adapters.ListAdapter;

public class ListFragment extends Fragment {

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);

        List<Recipe> data = new MockData().data;
        RecyclerView.LayoutManager lm = new LinearLayoutManager( getActivity() );
        binding.list.setLayoutManager(lm);
        binding.list.setHasFixedSize(true);
        ListAdapter adapter = new ListAdapter( data );
        binding.list.setAdapter(adapter);

        return binding.getRoot();

    }

}
