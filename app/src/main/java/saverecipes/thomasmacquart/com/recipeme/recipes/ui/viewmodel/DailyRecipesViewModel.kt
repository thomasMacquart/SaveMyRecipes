package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.core.utils.AsyncResponse
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.DailyRecipesRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import javax.inject.Inject

class DailyRecipesViewModel @Inject constructor(private val repo : DailyRecipesRepo ) : ViewModel() {
    val recipesObservable : MutableLiveData<DailyRecipesState> = MutableLiveData()

    fun loadDailyRecipes() {
        recipesObservable.value = DailyRecipesState.Loading
        viewModelScope.launch {
            val result = repo.getDailyRecipes()
            when (result) {
                is AsyncResponse.Success -> onSuccess(result.data)
                is AsyncResponse.Failed -> onError(result.exception)
            }.exhaustive
        }
    }

    private fun onSuccess(recipes : List<Recipe>) {
        if (recipes == null || recipes.isEmpty()) {
            recipesObservable.value = DailyRecipesState.ShowEmpty
        } else {
            recipesObservable.value = DailyRecipesState.ShowRecipes(recipes)
        }
    }

    private fun onError(e : Throwable) {
        recipesObservable.value = DailyRecipesState.ShowError("oops")
    }

    class Factory(
            private val repo: DailyRecipesRepo
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            DailyRecipesViewModel(repo) as T
    }
}

sealed class DailyRecipesState {
    data class ShowRecipes(val recipes : List<Recipe>) : DailyRecipesState()
    object Loading : DailyRecipesState()
    data class ShowError(val errStr : String) : DailyRecipesState()
    object ShowEmpty : DailyRecipesState()
}