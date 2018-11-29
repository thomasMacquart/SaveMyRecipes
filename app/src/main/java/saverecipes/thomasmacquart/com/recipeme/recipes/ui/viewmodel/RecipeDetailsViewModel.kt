package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeDetailsUseCase
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.model.RecipeDetailsModelView
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor(val usecase : RecipeDetailsUseCase) : ViewModel() {

    val recipeObservable : MutableLiveData<RecipeDetailsModelView> = MutableLiveData()

    fun getRecipe(id : Long) {
        usecase.getRecipe(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onRecipeReceived, this::onError)
    }

    private fun onRecipeReceived(recipe : RecipeDetailsModelView) {
        recipeObservable.value = recipe
    }

    private fun onError(error : Throwable) {

    }
}