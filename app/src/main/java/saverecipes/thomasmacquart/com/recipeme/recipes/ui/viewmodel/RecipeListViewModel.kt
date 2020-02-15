package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.core.utils.AsyncResponse
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepoImpl
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListState.EmptyState
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListState.ErrorState
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListState.LoadingState
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListState.SuccessState
import javax.inject.Inject

/**
 * Created by thomas.macquart on 14/02/2018.
 */
open class RecipeListViewModel @Inject constructor(private val repo : RecipeRepoImpl) : ViewModel() {

    val recipes: MutableLiveData<RecipeListState> = MutableLiveData()

    open fun loadRecipes()  {
        recipes.value = LoadingState
        viewModelScope.launch {
            val result = repo.getRecipes()
            when (result) {
                is AsyncResponse.Success -> onRecipesReceived(result.data)
                is AsyncResponse.Failed -> onError(result.exception)
            }.exhaustive
        }
    }

    private fun onRecipesReceived(recipesList : List<Recipe>) {
        if (recipesList.isEmpty()) {
            recipes.value = EmptyState
        } else {
            recipes.value = SuccessState(recipesList)
        }
    }

    private fun onError(error: Throwable) {
        //todo localize it
        recipes.value = ErrorState("something went wrong")
    }

    class Factory(
            private val repo: RecipeRepoImpl
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            RecipeListViewModel(repo) as T

    }
}

sealed class RecipeListState {
    data class SuccessState(val recipes : List<Recipe>) : RecipeListState()
    data class ErrorState(val message : String) : RecipeListState()
    object EmptyState : RecipeListState()
    object LoadingState : RecipeListState()
}
