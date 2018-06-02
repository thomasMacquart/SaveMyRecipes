package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import android.arch.lifecycle.ViewModel
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor(repo : RecipeRepo) : ViewModel() {

    private val repository : RecipeRepo = repo;

    fun getRecipe(recipeId : String) {

    }
}