package saverecipes.thomasmacquart.com.recipeme.recipes.presenter

import saverecipes.thomasmacquart.com.recipeme.core.BasePresenter
import saverecipes.thomasmacquart.com.recipeme.recipes.dao.RecipeDao
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.RecipeListView

/**
 * Created by thomas.macquart on 07/01/2018.
 */
class RecipesListPresenter constructor(val recipeDao : RecipeDao) : BasePresenter<RecipeListView>() {

    fun getRecipes() {
        view?.onDataFetched(recipeDao.getRecipes())
    }
}