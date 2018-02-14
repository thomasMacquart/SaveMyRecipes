package saverecipes.thomasmacquart.com.recipeme.recipes.ui

import saverecipes.thomasmacquart.com.recipeme.core.MvpView
import saverecipes.thomasmacquart.com.recipeme.recipes.data.Recipe

/**
 * Created by thomas.macquart on 07/01/2018.
 */
interface RecipeListView : MvpView {
    fun onDataFetched(recipes : List<Recipe>)
}