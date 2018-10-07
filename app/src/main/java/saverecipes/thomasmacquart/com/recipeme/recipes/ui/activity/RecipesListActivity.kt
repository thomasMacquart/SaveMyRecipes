package saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.recipes_list_activity.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.adapter.RecipesListAdapter
import saverecipes.thomasmacquart.com.recipeme.recipes.model.RecipesListModel
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListViewModel
import javax.inject.Inject
import android.app.ProgressDialog
import android.view.View


class RecipesListActivity : AppCompatActivity(), HasActivityInjector {


    @Inject
    lateinit var activityDispatchingAndroidInjector : DispatchingAndroidInjector<Activity>

    lateinit var adapter : RecipesListAdapter
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
        model.loadRecipes()

        recipes_list.layoutManager = LinearLayoutManager(this)

        simpleProgressBar.visibility = View.VISIBLE
        doRequest()

    }

    fun doRequest() {
        model.recipes.observe(this, object : Observer<RecipesListModel> {
            override fun onChanged(@Nullable recipesListModel: RecipesListModel?) {
                if (recipesListModel != null) {
                    if (recipesListModel.isLoading) {
                        simpleProgressBar.visibility = View.VISIBLE
                    } else {
                        simpleProgressBar.visibility = View.GONE
                    }
                    adapter = RecipesListAdapter(recipesListModel.recipesListResult) {
                        startActivity(RecipeDetailsActivity.getStartIntent(this@RecipesListActivity, it.id))
                    }
                    recipes_list.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun goToCreateRecipe() {
        startActivity(this.UserDetailIntent())
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}
