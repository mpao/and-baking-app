package io.github.mpao.baking.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import io.github.mpao.baking.databinding.StepRowBinding;
import io.github.mpao.baking.entities.Step;
import io.github.mpao.baking.ui.FragmentConnector;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder>{

    private List<Step> list;
    private FragmentConnector connector;

    public StepsAdapter(List<Step> list, FragmentConnector connector){
        this.list = list;
        this.connector = connector;
    }

    @Override
    @NonNull
    public StepsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from( parent.getContext() );
        StepRowBinding bind = StepRowBinding.inflate( layoutInflater, parent, false);
        return new ViewHolder( bind );

    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.ViewHolder holder, int position) {

        Step step = list.get(position);
        holder.bind( step );

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

        public void bind(Step step){
            bind.setStep(step);
            /*
             * Click on item:
             * interface ( DetailActivity ) accomplishes the selection according to the layout;
             * populate the fragments in tablet or start new activity
             */
            bind.title.setOnClickListener( view -> connector.onElementSelected(step) );
            bind.executePendingBindings();
        }

    }

}
