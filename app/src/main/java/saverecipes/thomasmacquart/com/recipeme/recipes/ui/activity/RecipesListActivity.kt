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
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListViewModel
import javax.inject.Inject


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

        recipes_list.layoutManager = LinearLayoutManager(this)


        doRequest()

    }

    fun doRequest() {
        model.getRecipes().observe(this, object : Observer<List<Recipe>> {
            override fun onChanged(@Nullable recipes: List<Recipe>?) {
                if (recipes != null) {
                    adapter = RecipesListAdapter(recipes) {
                        //onitemclick
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
