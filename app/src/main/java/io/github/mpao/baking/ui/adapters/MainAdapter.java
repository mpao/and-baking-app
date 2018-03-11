package io.github.mpao.baking.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import java.util.List;
import io.github.mpao.baking.databinding.MainRowBinding;
import io.github.mpao.baking.di.App;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.ui.DetailActivity;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private final List<Recipe> list;
    private Context context;

    public MainAdapter(List<Recipe> list){

        this.list = list;

    }

    @Override
    @NonNull
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from( context );
        MainRowBinding bind = MainRowBinding.inflate( layoutInflater, parent, false);
        return new ViewHolder( bind );

    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {

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

        private final MainRowBinding bind;

        private ViewHolder( MainRowBinding binding ){
            super( binding.getRoot() );
            this.bind = binding;
        }

        public void bind(final Recipe recipe){
            bind.setRecipe( recipe );
            if( recipe.getImage() != null && recipe.getImage().length()>0 )
                Picasso.with(context).load( recipe.getImage() ).into(bind.image);
            bind.row.setOnClickListener( view -> {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(App.INTENT_NAME, recipe);
                context.startActivity(intent);
            } );
            bind.executePendingBindings();
        }

    }

}
