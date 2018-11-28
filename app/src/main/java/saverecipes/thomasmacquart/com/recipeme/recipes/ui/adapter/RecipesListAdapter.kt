package saverecipes.thomasmacquart.com.recipeme.recipes.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.recipe_row_layout.view.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe

/**
 * Created by thomas.macquart on 06/01/2018.
 */
class RecipesListAdapter(private val listener: (Recipe) -> Unit) : RecyclerView.Adapter<RecipesListAdapter.RecipeViewHolder>() {

    var items : List<Recipe> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.recipe_row_layout, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size


    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title : TextView = itemView.recipe_row_title
        val type : TextView = itemView.recipe_row_type

        fun bind(recipe: Recipe, listener: (Recipe) -> Unit) = with(itemView) {
            title.text = recipe.name
            type.text = recipe.type
            setOnClickListener { listener(recipe) }
        }
    }
}