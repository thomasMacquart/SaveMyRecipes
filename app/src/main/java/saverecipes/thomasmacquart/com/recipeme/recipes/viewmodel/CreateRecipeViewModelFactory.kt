package saverecipes.thomasmacquart.com.recipeme.recipes.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import saverecipes.thomasmacquart.com.recipeme.recipes.data.RecipeRepo
import javax.inject.Inject

/**
 * Created by thomas.macquart on 21/03/2018.
 */
class CreateRecipeViewModelFactory @Inject constructor(private val repo: RecipeRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateRecipeViewModel::class.java!!)) {
            return CreateRecipeViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}