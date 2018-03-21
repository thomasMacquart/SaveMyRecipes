package saverecipes.thomasmacquart.com.recipeme.recipes.viewmodel

import android.arch.lifecycle.ViewModel
import saverecipes.thomasmacquart.com.recipeme.recipes.dao.RecipeDao
import saverecipes.thomasmacquart.com.recipeme.recipes.data.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.data.RecipeRepo
import javax.inject.Inject

/**
 * Created by thomas.macquart on 21/03/2018.
 */
//todo interface
class CreateRecipeViewModel @Inject constructor(repo : RecipeRepo) : ViewModel(){

    private val repository : RecipeRepo = repo;

    fun createRecipe(recipe : Recipe) {
        repository.addRecipe(recipe)
    }
}