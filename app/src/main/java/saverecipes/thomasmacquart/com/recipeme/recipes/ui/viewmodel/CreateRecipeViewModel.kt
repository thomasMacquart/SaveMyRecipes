package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import javax.inject.Inject

/**
 * Created by thomas.macquart on 21/03/2018.
 */
//todo interface
class CreateRecipeViewModel @Inject constructor(repo : RecipeRepo) : ViewModel(){

    private val repository : RecipeRepo = repo;

    fun createRecipe(recipe : Recipe) = launch(CommonPool) {
        repository.addRecipe(recipe)
    }
}