package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import javax.inject.Inject

/**
 * Created by thomas.macquart on 21/03/2018.
 */
//todo interface
class CreateRecipeViewModel @Inject constructor(private val repo : RecipeRepo) : ViewModel(){

    val uiObservable = MutableLiveData<CreateRecipeState>()
    private var uri : String? = null

    private fun createRecipe(recipe : Recipe)  {
        recipe.imageUri = uri ?: ""
        repo.addRecipe(recipe)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun sendIntention(intention : CreateRecipesIntentions) {
        when (intention) {
            is CreateRecipesIntentions.ImageSelected -> {
                uri = intention.uri
                uiObservable.value = CreateRecipeState.ShowImage(intention.uri ?: "")
            }
            is CreateRecipesIntentions.CreateRecipe -> createRecipe(intention.recipe)
            is CreateRecipesIntentions.HandleError -> uiObservable.value = CreateRecipeState.ShowError("Something went wrong")
        }.exhaustive
    }

}

sealed class CreateRecipeState {
    data class ShowImage(val uri : String) : CreateRecipeState()
    data class ShowError(val error : String) : CreateRecipeState()
}

sealed class CreateRecipesIntentions {
    data class ImageSelected(val uri : String?) : CreateRecipesIntentions()
    data class CreateRecipe(val recipe : Recipe) : CreateRecipesIntentions()
    object HandleError : CreateRecipesIntentions()
}