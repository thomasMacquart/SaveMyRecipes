package saverecipes.thomasmacquart.com.recipeme.recipes.ui.adapter

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.recipe_row_layout.view.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import java.io.File

/**
 * Created by thomas.macquart on 06/01/2018.
 */
class RecipesListAdapter(private val listener: (Recipe) -> Unit) : ListAdapter<Recipe, RecipesListAdapter.RecipeViewHolder>(DIFF_CALLBACK) {

    companion object {
        val  DIFF_CALLBACK : DiffUtil.ItemCallback<Recipe> = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.recipe_row_layout, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) = holder.bind(getItem(position), listener)


    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title : TextView = itemView.recipe_row_title
        val type : TextView = itemView.recipe_row_type
        val image : ImageView = itemView.recipe_image

        fun bind(recipe: Recipe, listener: (Recipe) -> Unit) = with(itemView) {
            val requestOption = RequestOptions()
                    .placeholder(R.drawable.recipe_empty_state_icon).centerCrop()

            title.text = recipe.name
            type.text = recipe.type
            Glide.with(this.context)
                    .load(File(Uri.parse(recipe.imageUri).path))
                    .apply(requestOption)
                    .into(image)
            setOnClickListener { listener(recipe) }
        }
    }
}