package saverecipes.thomasmacquart.com.recipeme.recipes.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import saverecipes.thomasmacquart.com.recipeme.RecipeMeApplication
import saverecipes.thomasmacquart.com.recipeme.recipes.dao.RecipeDao
import javax.inject.Inject

/**
 * Created by thomas.macquart on 27/02/2018.
 */
//todo implement interface
class RecipeRepo {

    var mDao : RecipeDao

    private val recipes = MutableLiveData<List<Recipe>>()

    @Inject constructor(dao : RecipeDao) {
        mDao = dao
    }

    fun getRecipes() :LiveData<List<Recipe>> {
        recipes.value = mDao.getRecipes()
        return recipes
    }

    //todo return boolean to confirm? reactive app?
    fun addRecipe(recipe : Recipe) {
        mDao.saveRecipe(recipe)
    }
}