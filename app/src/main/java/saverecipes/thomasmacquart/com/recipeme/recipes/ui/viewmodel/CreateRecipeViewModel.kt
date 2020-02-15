package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepoImpl
import javax.inject.Inject

/**
 * Created by thomas.macquart on 21/03/2018.
 */
//todo interface
class CreateRecipeViewModel @Inject constructor(private val repo : RecipeRepoImpl) : ViewModel(){

    val uiObservable = MutableLiveData<CreateRecipeState>()

    private fun createRecipe(recipe : Recipe)  {
        viewModelScope.launch {
            repo.addRecipe(recipe)
        }
    }

    fun sendIntention(intention : CreateRecipesIntentions) {
        when (intention) {
            is CreateRecipesIntentions.CreateRecipe -> createRecipe(intention.recipe)
        }.exhaustive
    }

    class Factory(
            private val repo: RecipeRepoImpl
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            CreateRecipeViewModel(repo) as T

    }

    companion object {

        fun obtain(scope: FragmentActivity, factory: CreateRecipeViewModel.Factory): CreateRecipeViewModel =
            ViewModelProviders.of(scope, factory)[CreateRecipeViewModel::class.java]
    }
}

sealed class CreateRecipeState {
    data class ShowError(val error : String) : CreateRecipeState()
}

sealed class CreateRecipesIntentions {
    data class CreateRecipe(val recipe : Recipe) : CreateRecipesIntentions()
}