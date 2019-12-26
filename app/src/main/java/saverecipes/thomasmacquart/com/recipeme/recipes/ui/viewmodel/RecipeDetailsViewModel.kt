package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor(private val repo : RecipeRepo) : ViewModel() {

    val recipeObservableUi : MutableLiveData<RecipeDetailsState> = MutableLiveData()
    lateinit var _recipe : Recipe
    private val disposable = CompositeDisposable()

    fun getRecipe(id : Long) {
        recipeObservableUi.value = RecipeDetailsState.Loading
        disposable.add(repo.getRecipe(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onRecipeReceived, this::onError))
    }

    private fun onRecipeReceived(recipe : Recipe) {
        _recipe = recipe
        recipeObservableUi.value = RecipeDetailsState.OnSuccess(recipe)
    }

    private fun onError(error : Throwable) {
        recipeObservableUi.value = RecipeDetailsState.OnError("oops")
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun onDelete() {
        //disposable.add(repo.deleteRecipe())
    }
}

sealed class RecipeDetailsState {
    data class OnSuccess(val recipe : Recipe) : RecipeDetailsState()
    data class OnError(val error: String) : RecipeDetailsState()
    object  Loading : RecipeDetailsState()
}