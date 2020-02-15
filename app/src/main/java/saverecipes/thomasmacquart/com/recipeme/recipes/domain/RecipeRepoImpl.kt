package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import saverecipes.thomasmacquart.com.recipeme.core.utils.AsyncResponse
import saverecipes.thomasmacquart.com.recipeme.recipes.dao.RecipeDao
import javax.inject.Inject

/**
 * Created by thomas.macquart on 27/02/2018.
 */
open class RecipeRepoImpl @Inject constructor(private val mDao: RecipeDao): RecipeRepo{


    override suspend fun getRecipes(): AsyncResponse<List<Recipe>> {
        return withContext(Dispatchers.IO) {AsyncResponse.Success(mDao.getRecipes())}
    }

    override suspend fun addRecipe(recipe: Recipe) {
       return  withContext(Dispatchers.IO) {mDao.saveRecipe(recipe)}
    }

    override suspend fun getRecipe(id: Long): AsyncResponse<Recipe> {
        return withContext(Dispatchers.IO) {AsyncResponse.Success(mDao.findRecipeById(id))}
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        return withContext(Dispatchers.IO) {mDao.deleteRecipe(recipe)}
    }
}