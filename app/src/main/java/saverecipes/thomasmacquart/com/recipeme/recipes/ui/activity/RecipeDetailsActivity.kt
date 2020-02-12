package saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.recipe_details_activity.recipe_details_delete_button
import kotlinx.android.synthetic.main.recipe_details_activity.state_layout
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.RecipeMeApplication
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.databinding.RecipeDetailsActivityBinding
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeDetailsState
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeDetailsViewModel
import javax.inject.Inject

class RecipeDetailsActivity : AppCompatActivity(){

    @Inject
    lateinit var factory: RecipeDetailsViewModel.Factory

    private lateinit var viewModel: RecipeDetailsViewModel

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
        val recipesComponent = (applicationContext as RecipeMeApplication).appComponent.recipesComponent().create()
        recipesComponent.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = RecipeDetailsViewModel.obtain(this, factory)

        binding = DataBindingUtil.setContentView(
                this, R.layout.recipe_details_activity)

        binding.setLifecycleOwner(this)

        observe()

        doRequest()

        recipe_details_delete_button.setOnClickListener {
            viewModel.onDelete()
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
                RecipeDetailsState.OnDelete -> this.finish()
            }.exhaustive
        })
    }

    private fun populateUi(recipe : Recipe) {
        state_layout.showContent()
        binding.recipe = recipe
    }
}