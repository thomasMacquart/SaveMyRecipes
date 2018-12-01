package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import saverecipes.thomasmacquart.com.recipeme.recipes.dao.RecipeDao
import javax.inject.Inject

/**
 * Created by thomas.macquart on 27/02/2018.
 */
//todo implement interface
class RecipeRepo {

    var mDao: RecipeDao


    @Inject constructor(dao: RecipeDao) {
        mDao = dao
    }


    fun getRecipes(): Flowable<List<Recipe>> {
        return mDao.getRecipes()
    }

    fun addRecipe(recipe: Recipe) : Completable {
       return Completable.fromAction { mDao.saveRecipe(recipe)}
    }

    fun getRecipe(id : Long) : Single<Recipe> {
        return mDao.findRecipeById(id)
    }
}