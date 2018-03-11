package io.github.mpao.baking.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import io.github.mpao.baking.R;
import io.github.mpao.baking.databinding.ListRowBinding;
import io.github.mpao.baking.entities.Recipe;
import io.github.mpao.baking.ui.RecipeActivity;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder>{

    private final List<Recipe> list;
    private Context context;

    public RecipesAdapter(List<Recipe> list){

        this.list = list;

    }

    @Override
    @NonNull
    public RecipesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from( context );
        ListRowBinding bind = ListRowBinding.inflate( layoutInflater, parent, false);
        return new ViewHolder( bind );

    }

    @Override
    public void onBindViewHolder(@NonNull RecipesAdapter.ViewHolder holder, int position) {

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
            // recyclerview inside another.
            // testing some strange layouts, but they aren't so cool as aspect
            RecyclerView.LayoutManager lm = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            DividerItemDecoration itemDecorator = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
            itemDecorator.setDrawable( context.getDrawable(R.drawable.default_divider) );
            bind.ingredients.addItemDecoration(itemDecorator);
            bind.ingredients.setLayoutManager(lm);
            bind.ingredients.setHasFixedSize(true);
            bind.ingredients.setAdapter( new IngredientsAdapter(recipe.getIngredients()) );

            bind.row.setOnClickListener( view -> {
                Intent intent = new Intent(context, RecipeActivity.class);
                intent.putExtra("value", recipe);
                context.startActivity(intent);
            } );
            bind.executePendingBindings();
        }

    }

}