package io.github.mpao.baking.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import io.github.mpao.baking.databinding.ListRowBinding;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.ui.FragmentConnector;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private final List<Recipe> list;
    private FragmentConnector connector;

    public ListAdapter(List<Recipe> list, FragmentConnector connector){

        this.list = list;
        this.connector = connector;

    }

    @Override
    @NonNull
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from( parent.getContext() );
        ListRowBinding bind = ListRowBinding.inflate( layoutInflater, parent, false);
        return new ViewHolder( bind );

    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {

        Recipe recipe = list.get(position);
        holder.bind( recipe );

    }

    @Override
    public int getItemCount() {

        return list != null ? list.size() :  0;

    }

    /*
     * ViewHolder class
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ListRowBinding bind;

        private ViewHolder( ListRowBinding binding ){
            super( binding.getRoot() );
            this.bind = binding;
        }

        public void bind(final Recipe recipe){
            bind.setRecipe( recipe );
            bind.row.setOnClickListener( view -> connector.onElementSelected(recipe) );
            bind.executePendingBindings();
        }

    }

}
