package saverecipes.thomasmacquart.com.recipeme.recipes.ui

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.recipes_list_activity.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.RecipeMeApplication
import saverecipes.thomasmacquart.com.recipeme.recipes.adapter.RecipesListAdapter
import saverecipes.thomasmacquart.com.recipeme.recipes.data.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.presenter.RecipesListPresenter
import javax.inject.Inject

class RecipesListActivity : Activity(), RecipeListView {

    @Inject
    lateinit var presenter : RecipesListPresenter

    lateinit var adapter : RecipesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes_list_activity)
        (application as RecipeMeApplication).recipeComponent.inject(this)

        create_recipe_button.setOnClickListener {
            goToCreateRecipe()
        }

        //presenter = RecipesListPresenter(RecipeMeApplication.database.RecipeDao())
        presenter.bind(this)
        presenter.getRecipes()
    }

    override fun onDataFetched(recipes: List<Recipe>) {
        adapter = RecipesListAdapter(recipes) {

        }
        recipes_list.adapter = adapter
        recipes_list.layoutManager = LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }

    private fun goToCreateRecipe() {
        startActivity(this.UserDetailIntent())
    }
}
