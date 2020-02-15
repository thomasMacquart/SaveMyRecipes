package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.core.utils.AsyncResponse
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepoImpl
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor(private val repo : RecipeRepoImpl) : ViewModel() {

    val recipeObservableUi : MutableLiveData<RecipeDetailsState> = MutableLiveData()
    private lateinit var _recipe : Recipe

    fun getRecipe(id : Long) {
        recipeObservableUi.value = RecipeDetailsState.Loading
        viewModelScope.launch {
            val result = repo.getRecipe(id)
            when (result) {
                is AsyncResponse.Success -> onRecipeReceived(result.data)
                is AsyncResponse.Failed ->onError(result.exception)
            }.exhaustive
        }
    }

    private fun onRecipeReceived(recipe : Recipe) {
        _recipe = recipe
        recipeObservableUi.value = RecipeDetailsState.OnSuccess(recipe)
    }

    private fun onError(error : Throwable) {
        recipeObservableUi.value = RecipeDetailsState.OnError("oops")
    }

    fun onDelete() {
        viewModelScope.launch {
            repo.deleteRecipe(_recipe)
            recipeObservableUi.value = RecipeDetailsState.OnDelete
        }
    }

    class Factory(
            private val repo: RecipeRepoImpl
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            RecipeDetailsViewModel(repo) as T

    }

    companion object {

        fun obtain(scope: FragmentActivity, factory: Factory): RecipeDetailsViewModel =
            ViewModelProviders.of(scope, factory)[RecipeDetailsViewModel::class.java]
    }
}

sealed class RecipeDetailsState {
    data class OnSuccess(val recipe : Recipe) : RecipeDetailsState()
    data class OnError(val error: String) : RecipeDetailsState()
    object  Loading : RecipeDetailsState()
    object OnDelete : RecipeDetailsState()
}