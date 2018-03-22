package saverecipes.thomasmacquart.com.recipeme.recipes.ui

import android.app.Activity
import android.arch.lifecycle.Observer
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
import saverecipes.thomasmacquart.com.recipeme.recipes.adapter.RecipesListAdapter
import saverecipes.thomasmacquart.com.recipeme.recipes.data.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.viewmodel.RecipeListViewModel
import saverecipes.thomasmacquart.com.recipeme.recipes.viewmodel.RecipeListViewModelFactory
import javax.inject.Inject
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent


class RecipesListActivity : AppCompatActivity(), HasActivityInjector {

    private val CREATE_RECIPE_INTENT : Int = 100

    @Inject
    lateinit var activityDispatchingAndroidInjector : DispatchingAndroidInjector<Activity>

    lateinit var adapter : RecipesListAdapter
    @Inject
    lateinit var factory : RecipeListViewModelFactory

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == CREATE_RECIPE_INTENT && resultCode == Activity.RESULT_OK) {
            doRequest()
        }
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
        startActivityForResult(this.UserDetailIntent(), CREATE_RECIPE_INTENT)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}
