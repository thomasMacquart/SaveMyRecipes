package saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.recipe_details_activity.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.core.BaseViewModelActivity
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.databinding.RecipeDetailsActivityBinding
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeDetailsState
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeDetailsViewModel

class RecipeDetailsActivity : BaseViewModelActivity<RecipeDetailsViewModel>(){

    companion object {
        private val RECIPE_ID_BUNDLE  = "RECIPE_ID_BUNDLE"

        fun getStartIntent(context : Context, recipeId : Long) : Intent{
            val intent = Intent(context, RecipeDetailsActivity::class.java)
            intent.putExtra(RECIPE_ID_BUNDLE, recipeId)
            return intent
        }
    }

    private lateinit var binding : RecipeDetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
                this, R.layout.recipe_details_activity)

        binding.setLifecycleOwner(this)

        observe()

        doRequest()

        recipe_details_delete_button.setOnClickListener {

        }
    }

    private fun doRequest() {
        viewModel.getRecipe(intent.getLongExtra(RECIPE_ID_BUNDLE, 0))
    }

    private fun observe() {
        viewModel.recipeObservableUi.observe(this, Observer {
            when (it) {
                is RecipeDetailsState.OnSuccess -> populateUi(it.recipe)
                is RecipeDetailsState.OnError -> state_layout.showError(it.error) {doRequest()}
                is RecipeDetailsState.Loading -> state_layout.showLoading()
            }.exhaustive
        })
    }

    private fun populateUi(recipe : Recipe) {
        state_layout.showContent()
        binding.recipe = recipe
    }

    override fun createViewModel(): RecipeDetailsViewModel {
        return ViewModelProviders.of(this, factory)
                .get(RecipeDetailsViewModel::class.java)
    }
}