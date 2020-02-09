package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepoImpl
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor(private val repo : RecipeRepoImpl) : ViewModel() {

    val recipeObservableUi : MutableLiveData<RecipeDetailsState> = MutableLiveData()
    private lateinit var _recipe : Recipe
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
        Completable.fromAction { repo.deleteRecipe(_recipe) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onRecipeDeleted, this::onError)
    }

    private fun onRecipeDeleted() {
        recipeObservableUi.value = RecipeDetailsState.OnDelete
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