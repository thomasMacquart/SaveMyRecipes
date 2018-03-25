package saverecipes.thomasmacquart.com.recipeme.recipes.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import saverecipes.thomasmacquart.com.recipeme.recipes.dao.RecipeDao
import javax.inject.Inject

/**
 * Created by thomas.macquart on 27/02/2018.
 */
//todo implement interface
class RecipeRepo {

    var mDao: RecipeDao

    var mObservableRecipes: MediatorLiveData<List<Recipe>>

    @Inject constructor(dao: RecipeDao) {
        mDao = dao
        mObservableRecipes = MediatorLiveData<List<Recipe>>()

        mObservableRecipes.addSource(dao.getRecipes(),
                { recipeEntities ->
                    mObservableRecipes.postValue(recipeEntities)
                })
    }

    fun getRecipes(): LiveData<List<Recipe>> {
        return mObservableRecipes
    }

    //todo return boolean to confirm? reactive app?
    fun addRecipe(recipe: Recipe) {
        mDao.saveRecipe(recipe)
    }
}