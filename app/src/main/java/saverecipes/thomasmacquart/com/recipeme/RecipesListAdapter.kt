package saverecipes.thomasmacquart.com.recipeme

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recipe_row_layout.view.*

/**
 * Created by thomas.macquart on 06/01/2018.
 */
class RecipesListAdapter(val items : List<Recipe>, val listener: (Recipe) -> Unit) : RecyclerView.Adapter<RecipesListAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.recipe_row_layout, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size


    class RecipeViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(recipe: Recipe, listener: (Recipe) -> Unit) = with(itemView) {
            recipe_row_title.text = recipe.name
            setOnClickListener { listener(recipe) }
        }
    }
}