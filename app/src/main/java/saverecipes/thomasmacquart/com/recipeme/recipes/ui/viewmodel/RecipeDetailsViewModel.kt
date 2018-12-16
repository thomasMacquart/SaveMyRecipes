package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.IRecipeDetailsUserCase
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeDetailsUseCase
import saverecipes.thomasmacquart.com.recipeme.recipes.model.RecipeDetailsUiModel
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor(private val usecase : IRecipeDetailsUserCase) : ViewModel() {

    val recipeObservableUi : MutableLiveData<RecipeDetailsState> = MutableLiveData()

    fun getRecipe(id : Long) {
        recipeObservableUi.value = RecipeDetailsState.Loading
        usecase.getRecipe(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onRecipeReceived, this::onError)
    }

    private fun onRecipeReceived(recipeUi : RecipeDetailsUiModel) {
        recipeObservableUi.value = RecipeDetailsState.OnSuccess(recipeUi)
    }

    private fun onError(error : Throwable) {
        recipeObservableUi.value = RecipeDetailsState.OnError(error)
    }
}

sealed class RecipeDetailsState {
    data class OnSuccess(val recipeModel : RecipeDetailsUiModel) : RecipeDetailsState()
    data class OnError(val error: Throwable) : RecipeDetailsState()
    object  Loading : RecipeDetailsState()
}