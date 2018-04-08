package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.model.RecipesListModel
import javax.inject.Inject


/**
 * Created by thomas.macquart on 14/02/2018.
 */
open class RecipeListViewModel @Inject constructor(repo : RecipeRepo) : ViewModel() {
    private val recipes: LiveData<RecipesListModel> = repo.getRecipes()

    open fun getRecipes() : LiveData<RecipesListModel> {
        return recipes;
    }
}