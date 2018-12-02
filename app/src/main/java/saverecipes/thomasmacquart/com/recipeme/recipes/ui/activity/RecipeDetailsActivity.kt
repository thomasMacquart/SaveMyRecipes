package saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity

import android.app.Activity
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.create_recipe_activity.*
import kotlinx.android.synthetic.main.recipe_details_activity.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.recipes.model.RecipeDetailsUiModel
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeDetailsState
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeDetailsViewModel
import javax.inject.Inject

class RecipeDetailsActivity : AppCompatActivity(){


    companion object {
        private val RECIPE_ID_BUNDLE  = "RECIPE_ID_BUNDLE"

        fun getStartIntent(context : Context, recipeId : Long) : Intent{
            val intent : Intent = Intent(context, RecipeDetailsActivity::class.java)
            intent.putExtra(RECIPE_ID_BUNDLE, recipeId)
            return intent
        }
    }

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var factory: ViewModelFactory<RecipeDetailsViewModel>

    lateinit var model: RecipeDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.recipe_details_activity)

        model = createViewModel()
        observe()

        doRequest()
    }

    private fun doRequest() {
        model.getRecipe(intent.getLongExtra(RECIPE_ID_BUNDLE, 0))
    }

    //TODO : make a base class
    private fun observe() {
        model.recipeObservableUi.observe(this, Observer { recipe ->
            when (recipe) {
                is RecipeDetailsState.OnSuccess -> populateUi(recipe.recipeModel)
                is RecipeDetailsState.OnError -> state_layout.showError(recipe.error.message) {doRequest()}
                is RecipeDetailsState.Loading -> state_layout.showLoading()
            }.exhaustive
        })
    }

    private fun populateUi(recipe : RecipeDetailsUiModel) {
        state_layout.showContent()
        recipe_title.text = recipe.title
        recipe_type.text = recipe.type
        recipe_description.text = recipe.description

        Glide.with(this).load(recipe.uri)
                .into(detail_image)
    }

    //TODO : make a base class
    private fun createViewModel(): RecipeDetailsViewModel {
        return ViewModelProviders.of(this, factory)
                .get(RecipeDetailsViewModel::class.java!!)
    }
}