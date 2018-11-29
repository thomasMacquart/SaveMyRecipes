package saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.recipes_list_activity.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.adapter.RecipesListAdapter
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListState
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListViewModel
import javax.inject.Inject


class RecipesListActivity : AppCompatActivity(), HasActivityInjector {


    @Inject
    lateinit var activityDispatchingAndroidInjector : DispatchingAndroidInjector<Activity>

    private lateinit var recipesAdapter : RecipesListAdapter
    private lateinit var recipesLayoutManager: RecyclerView.LayoutManager
    @Inject
    lateinit var factory : ViewModelFactory<RecipeListViewModel>

    private lateinit var model : RecipeListViewModel

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
            when (it) {
                is RecipeListState.SuccessState -> {
                    state_layout.showContent()
                    updateList(it.recipes)
                }
                is RecipeListState.LoadingState -> state_layout.showLoading()
                is RecipeListState.ErrorState -> state_layout.showError(it.message) {model.loadRecipes()}
                is RecipeListState.EmptyState -> state_layout.showEmpty()
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
