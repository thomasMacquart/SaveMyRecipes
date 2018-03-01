package saverecipes.thomasmacquart.com.recipeme.recipes.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.recipes_list_activity.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.RecipeMeApplication
import saverecipes.thomasmacquart.com.recipeme.recipes.adapter.RecipesListAdapter
import saverecipes.thomasmacquart.com.recipeme.recipes.data.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.data.RecipeViewModel


class RecipesListActivity : AppCompatActivity() {

    lateinit var adapter : RecipesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes_list_activity)
        (application as RecipeMeApplication).recipeComponent.inject(this)

        create_recipe_button.setOnClickListener {
            goToCreateRecipe()
        }

        recipes_list.layoutManager = LinearLayoutManager(this)
        recipes_list.adapter = adapter

        val model = ViewModelProviders.of(this).get(RecipeViewModel::class.java!!)
        model.getRecipes().observe(this, object : Observer<List<Recipe>> {
            override fun onChanged(@Nullable recipes: List<Recipe>?) {
                if (recipes != null) {
                    adapter = RecipesListAdapter(recipes) {
                        //onitemclick
                    }

                    adapter.notifyDataSetChanged()
                }
            }
        })
    }


    private fun goToCreateRecipe() {
        startActivity(this.UserDetailIntent())
    }
}
