package saverecipes.thomasmacquart.com.recipeme.recipes.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import saverecipes.thomasmacquart.com.recipeme.RecipeMeApplication
import saverecipes.thomasmacquart.com.recipeme.recipes.dao.RecipeDao

/**
 * Created by thomas.macquart on 27/02/2018.
 */
class RecipeRepo {
    private val recipes = MutableLiveData<List<Recipe>>()

    fun getRecipes() :LiveData<List<Recipe>> {
        var dao : RecipeDao = RecipeMeApplication.database.RecipeDao()
        recipes.value = dao.getRecipes()
        return recipes
    }
}