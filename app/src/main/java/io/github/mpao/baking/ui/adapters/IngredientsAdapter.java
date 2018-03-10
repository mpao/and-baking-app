package io.github.mpao.baking.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import io.github.mpao.baking.databinding.IngredientRowBinding;
import io.github.mpao.baking.entities.Ingredient;

/*
 * Proof of concept for Ingredients recylerview.
 */
public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private final List<Ingredient> list;

    public IngredientsAdapter(List<Ingredient> list){

        this.list = list;

    }

    @Override
    @NonNull
    public IngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from( parent.getContext() );
        IngredientRowBinding bind = IngredientRowBinding.inflate( layoutInflater, parent, false);
        return new ViewHolder( bind );

    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, int position) {

        Ingredient ingredient = list.get(position);
        holder.bind( ingredient );

    }

    @Override
    public int getItemCount() {

        return list != null ? list.size() :  0;

    }

    /*
     * ViewHolder class
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        private final IngredientRowBinding bind;

        private ViewHolder( IngredientRowBinding binding ){
            super( binding.getRoot() );
            this.bind = binding;
        }

        public void bind(final Ingredient ingredient){
            bind.setIngredient( ingredient );
            bind.executePendingBindings();
        }

    }

}
