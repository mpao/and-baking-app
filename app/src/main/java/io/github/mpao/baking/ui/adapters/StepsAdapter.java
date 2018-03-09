package io.github.mpao.baking.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import io.github.mpao.baking.databinding.StepRowBinding;
import io.github.mpao.baking.entities.Step;
import io.github.mpao.baking.ui.StepActivity;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder>{

    private final List<Step> list;
    private Context context;

    public StepsAdapter(List<Step> list){
        this.list = list;
    }

    @Override
    @NonNull
    public StepsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from( context );
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

        public void bind(final Step step){
            bind.setStep( step );
            bind.title.setOnClickListener( view -> {
                Intent intent = new Intent(context, StepActivity.class);
                intent.putExtra("value", step);
                context.startActivity(intent);
            } );
            bind.executePendingBindings();
        }

    }

}
