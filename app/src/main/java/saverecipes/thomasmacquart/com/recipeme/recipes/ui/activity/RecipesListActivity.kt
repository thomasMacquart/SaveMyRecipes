package saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.recipes_list_activity.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.adapter.RecipesListAdapter
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListViewModel
import javax.inject.Inject
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.View.VISIBLE
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListState


class RecipesListActivity : AppCompatActivity(), HasActivityInjector {


    @Inject
    lateinit var activityDispatchingAndroidInjector : DispatchingAndroidInjector<Activity>

    private lateinit var recipesAdapter : RecipesListAdapter
    private lateinit var recipesLayoutManager: RecyclerView.LayoutManager
    @Inject
    lateinit var factory : ViewModelFactory<RecipeListViewModel>

    lateinit var model : RecipeListViewModel

    fun createViewModel(): RecipeListViewModel {
        return ViewModelProviders.of(this, factory)
                .get(RecipeListViewModel::class.java!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes_list_activity)

        create_recipe_button.setOnClickListener {
            goToCreateRecipe()
        }

        model = createViewModel()

        recipesLayoutManager = LinearLayoutManager(this)
        recipesAdapter = RecipesListAdapter {
            startActivity(RecipeDetailsActivity.getStartIntent(this@RecipesListActivity, it.id))
        }

        recipes_list.apply {
            setHasFixedSize(true)
            layoutManager = recipesLayoutManager
            adapter = recipesAdapter
        }

        subscribe()

        model.loadRecipes()


    }

    private fun subscribe() {
        model.recipes.observe(this, Observer {
            simpleProgressBar.visibility = View.GONE
            when (it) {
                is RecipeListState.SuccessState -> updateList(it.recipes)
                is RecipeListState.LoadingState -> simpleProgressBar.visibility = VISIBLE
                is RecipeListState.ErrorState -> TODO()
                is RecipeListState.EmptyState -> TODO()
            }.exhaustive
        })
    }

    private fun updateList(recipes : List<Recipe>) {
        recipesAdapter.items = recipes
        recipesAdapter.notifyDataSetChanged()
    }

    private fun goToCreateRecipe() {
        startActivity(this.UserDetailIntent())
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}
