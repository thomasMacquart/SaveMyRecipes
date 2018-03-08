package saverecipes.thomasmacquart.com.recipeme.recipes.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import saverecipes.thomasmacquart.com.recipeme.recipes.data.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.data.RecipeRepo


/**
 * Created by thomas.macquart on 14/02/2018.
 */
class RecipeListViewModel : ViewModel() {
    private val recipes: LiveData<List<Recipe>> = RecipeRepo().getRecipes()

    fun getRecipes() : LiveData<List<Recipe>> {
        return recipes;
    }
}