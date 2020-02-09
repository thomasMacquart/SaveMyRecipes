package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepoImpl
import javax.inject.Inject

/**
 * Created by thomas.macquart on 21/03/2018.
 */
//todo interface
class CreateRecipeViewModel @Inject constructor(private val repo : RecipeRepoImpl) : ViewModel(){

    val uiObservable = MutableLiveData<CreateRecipeState>()
    private val disposable = CompositeDisposable()

    private fun createRecipe(recipe : Recipe)  {
        disposable.add(repo.addRecipe(recipe)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    fun sendIntention(intention : CreateRecipesIntentions) {
        when (intention) {
            is CreateRecipesIntentions.CreateRecipe -> createRecipe(intention.recipe)
        }.exhaustive
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
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