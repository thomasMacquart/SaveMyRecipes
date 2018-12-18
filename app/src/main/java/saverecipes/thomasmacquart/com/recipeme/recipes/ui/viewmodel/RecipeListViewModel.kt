package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListState.*
import javax.inject.Inject


/**
 * Created by thomas.macquart on 14/02/2018.
 */
open class RecipeListViewModel @Inject constructor(private val repo : RecipeRepo) : ViewModel() {

    val recipes: MutableLiveData<RecipeListState> = MutableLiveData()
    private val disposable  = CompositeDisposable()

    open fun loadRecipes()  {
        recipes.value = LoadingState
        disposable.add(repo.getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onRecipesReceived, this::onError))
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

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}

sealed class RecipeListState {
    data class SuccessState(val recipes : List<Recipe>) : RecipeListState()
    data class ErrorState(val message : String) : RecipeListState()
    object EmptyState : RecipeListState()
    object LoadingState : RecipeListState()
}
