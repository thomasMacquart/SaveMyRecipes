package saverecipes.thomasmacquart.com.recipeme.recipes.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel


/**
 * Created by thomas.macquart on 14/02/2018.
 */
class RecipeViewModel : ViewModel() {
    private val recipes: LiveData<List<Recipe>> = RecipeRepo().getRecipes()

    fun getRecipes() : LiveData<List<Recipe>> {
        return recipes;
    }
}