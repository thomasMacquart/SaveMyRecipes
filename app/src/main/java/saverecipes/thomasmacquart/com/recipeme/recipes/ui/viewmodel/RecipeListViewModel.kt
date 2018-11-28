package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.model.RecipesListModel
import javax.inject.Inject


/**
 * Created by thomas.macquart on 14/02/2018.
 */
open class RecipeListViewModel @Inject constructor(val repo : RecipeRepo) : ViewModel() {

    val recipes: MutableLiveData<RecipesListModel> = MutableLiveData<RecipesListModel>()

    open fun loadRecipes()  {
        repo.getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onRecipesReceived, this::onError)
    }

    private fun onRecipesReceived(recipesList : List<Recipe>) {
        val model = RecipesListModel(false, recipesList)
        recipes.value = model
    }

    private fun onError(error: Throwable) {

    }
}