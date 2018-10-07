package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.model.RecipesListModel
import javax.inject.Inject


/**
 * Created by thomas.macquart on 14/02/2018.
 */
open class RecipeListViewModel @Inject constructor(val repo : RecipeRepo) : ViewModel() {
    public lateinit var recipes: LiveData<RecipesListModel>;

    open fun loadRecipes()  = launch(CommonPool) {
        val result = repo.getRecipes()
        recipes = result
    }
}