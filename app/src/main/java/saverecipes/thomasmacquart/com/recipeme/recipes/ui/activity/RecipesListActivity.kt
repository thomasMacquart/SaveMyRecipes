package saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipes_list_activity.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.core.BaseViewModelActivity
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.adapter.RecipesListAdapter
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListState
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListViewModel


class RecipesListActivity : BaseViewModelActivity<RecipeListViewModel>() {



    private val recipesAdapter : RecipesListAdapter by lazy { RecipesListAdapter {
        startActivity(RecipeDetailsActivity.getStartIntent(this@RecipesListActivity, it.id))
    } }
    private lateinit var recipesLayoutManager: RecyclerView.LayoutManager

    override fun createViewModel(): RecipeListViewModel {
        return ViewModelProviders.of(this, factory)
                .get(RecipeListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes_list_activity)

        create_recipe_button.setOnClickListener {
            goToCreateRecipe()
        }
        recipesLayoutManager = LinearLayoutManager(this)

        recipes_list.apply {
            setHasFixedSize(true)
            layoutManager = recipesLayoutManager
            adapter = recipesAdapter
        }

        subscribe()

        viewModel.loadRecipes()


    }

    private fun subscribe() {
        viewModel.recipes.observe(this, Observer {
            when (it) {
                is RecipeListState.SuccessState -> {
                    state_layout.showContent()
                    updateList(it.recipes)
                }
                is RecipeListState.LoadingState -> state_layout.showLoading()
                is RecipeListState.ErrorState -> state_layout.showError(it.message) {viewModel.loadRecipes()}
                is RecipeListState.EmptyState -> state_layout.showEmpty()
            }.exhaustive
        })
    }

    private fun updateList(recipes : List<Recipe>) {
        recipesAdapter.items = recipes
        recipesAdapter.notifyDataSetChanged()
    }

    private fun goToCreateRecipe() {
        startActivity(CreateRecipeActivity.getStartIntent(this))
    }
}
