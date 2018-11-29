package saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity

import android.app.Activity
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.recipe_details_activity.*
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
        observe()

        model.getRecipe(intent.getLongExtra(RECIPE_ID_BUNDLE, 0))
    }

    private fun observe() {
        model.recipeObservable.observe(this, Observer {
            recipe_title.text = it.title
        })
    }

    fun createViewModel(): RecipeDetailsViewModel {
        return ViewModelProviders.of(this, factory)
                .get(RecipeDetailsViewModel::class.java!!)
    }
}