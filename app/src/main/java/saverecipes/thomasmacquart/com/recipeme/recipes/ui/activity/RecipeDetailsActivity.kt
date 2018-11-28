package saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
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
    }

    fun createViewModel(): RecipeDetailsViewModel {
        return ViewModelProviders.of(this, factory)
                .get(RecipeDetailsViewModel::class.java!!)
    }
}