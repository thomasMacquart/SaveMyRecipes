package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import saverecipes.thomasmacquart.com.recipeme.core.CoroutinesDispatcherProvider
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import javax.inject.Inject

/**
 * Created by thomas.macquart on 21/03/2018.
 */
//todo interface
class CreateRecipeViewModel @Inject constructor(repo : RecipeRepo, private val dispatcherProvider: CoroutinesDispatcherProvider) : ViewModel(){

    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.main + parentJob)

    private val repository : RecipeRepo = repo

    fun createRecipe(recipe : Recipe) = scope.launch(dispatcherProvider.computation) {
        repository.addRecipe(recipe)
    }
}