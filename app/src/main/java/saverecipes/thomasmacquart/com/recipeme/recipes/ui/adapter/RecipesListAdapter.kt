package saverecipes.thomasmacquart.com.recipeme.recipes.ui.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recipe_row_layout.view.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe

/**
 * Created by thomas.macquart on 06/01/2018.
 */
class RecipesListAdapter(val items : List<Recipe>, val listener: (Recipe) -> Unit) : RecyclerView.Adapter<RecipesListAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        /*var view : View = LayoutInflater.from(parent.context).inflate(R.layout.recipe_row_layout, parent, false)
        return RecipeViewHolder(view)*/

        val binding : RecipeItemBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.context), R.layout.recipe_row_layout,
                        parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.binding.setComment(items.get(position))
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size


    class RecipeViewHolder(binding : RecipeItemBinding) : RecyclerView.ViewHolder(binding.getRoot) {
        val binding = binding
    }
}