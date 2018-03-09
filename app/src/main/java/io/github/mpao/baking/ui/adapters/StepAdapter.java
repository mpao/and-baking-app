package io.github.mpao.baking.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import io.github.mpao.baking.databinding.StepRowBinding;
import io.github.mpao.baking.entities.Step;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.ViewHolder> {

    private final List<Step> list;

    public StepAdapter(List<Step> list){

        this.list = list;

    }

    @Override
    @NonNull
    public StepAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from( parent.getContext() );
        StepRowBinding bind = StepRowBinding.inflate( layoutInflater, parent, false);
        return new ViewHolder( bind );

    }

    @Override
    public void onBindViewHolder(@NonNull StepAdapter.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {

        return list != null ? list.size() :  0;

    }

    /*
     * ViewHolder class
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        private final StepRowBinding bind;

        private ViewHolder( StepRowBinding binding ){
            super( binding.getRoot() );
            this.bind = binding;
        }

        public void bind(final Step step){

        }

    }

}
