package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.DailyRecipesRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import javax.inject.Inject

class DailyRecipesViewModel @Inject constructor(private val repo : DailyRecipesRepo) : ViewModel() {
    val recipesObservable : MutableLiveData<DailyRecipesState> = MutableLiveData()
    private val disposable = CompositeDisposable()

    fun loadDailyRecipes() {
        recipesObservable.value = DailyRecipesState.Loading
        disposable.add(repo.getDailyRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError))
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