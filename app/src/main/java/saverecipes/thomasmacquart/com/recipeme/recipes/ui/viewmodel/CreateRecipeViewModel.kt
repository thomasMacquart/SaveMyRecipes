package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepoImpl
import javax.inject.Inject

/**
 * Created by thomas.macquart on 21/03/2018.
 */
//todo interface + notify view when update finished
class CreateRecipeViewModel @Inject constructor(private val repo : RecipeRepo) : ViewModel(){

    fun createRecipe(recipe : Recipe)  {
        repo.addRecipe(recipe)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}