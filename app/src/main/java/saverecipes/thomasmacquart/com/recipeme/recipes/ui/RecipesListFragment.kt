package saverecipes.thomasmacquart.com.recipeme.recipes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipes_list_fragment.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.core.BaseViewModelFragment
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity.CreateRecipeActivity
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity.RecipeDetailsActivity
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.adapter.RecipesListAdapter
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListState
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListViewModel

class RecipesListFragment : BaseViewModelFragment<RecipeListViewModel>() {

    companion object {
        fun newInstance() : RecipesListFragment {
            return RecipesListFragment()
        }
    }


    private val recipesAdapter : RecipesListAdapter by lazy { RecipesListAdapter {
        startActivity(activity?.let { it1 -> RecipeDetailsActivity.getStartIntent(it1, it.id) })
    } }
    private lateinit var recipesLayoutManager: RecyclerView.LayoutManager

    override fun createViewModel(): RecipeListViewModel {
        return ViewModelProviders.of(this, factory)
                .get(RecipeListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recipes_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_recipe_button.setOnClickListener {
            goToCreateRecipe()
        }
        recipesLayoutManager = LinearLayoutManager(activity)

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
        recipesAdapter.submitList(recipes)
    }

    private fun goToCreateRecipe() {
        startActivity(activity?.let { CreateRecipeActivity.getStartIntent(it) })
    }
}