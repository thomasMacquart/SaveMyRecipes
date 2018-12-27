package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import saverecipes.thomasmacquart.com.recipeme.recipes.dao.RecipeDao
import javax.inject.Inject

/**
 * Created by thomas.macquart on 27/02/2018.
 */
open class RecipeRepoImpl @Inject constructor(private val mDao: RecipeDao): RecipeRepo{

    override fun getRecipes(): Flowable<List<Recipe>> {
        return mDao.getRecipes()
    }

    override fun addRecipe(recipe: Recipe) : Completable {
       return Completable.fromAction { mDao.saveRecipe(recipe)}
    }

    override fun getRecipe(id : Long) : Single<Recipe> {
        return mDao.findRecipeById(id)
    }

    override fun deleteRecipe(recipe: Recipe) : Completable {
        return Completable.fromAction { mDao.deleteRecipe(recipe)}
    }
}