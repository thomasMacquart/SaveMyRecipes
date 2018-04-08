package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import saverecipes.thomasmacquart.com.recipeme.recipes.dao.RecipeDao
import saverecipes.thomasmacquart.com.recipeme.recipes.model.RecipesListModel
import javax.inject.Inject

/**
 * Created by thomas.macquart on 27/02/2018.
 */
//todo implement interface
class RecipeRepo {

    var mDao: RecipeDao

    var mObservableRecipes: MediatorLiveData<RecipesListModel>

    @Inject constructor(dao: RecipeDao) {
        mDao = dao
        mObservableRecipes = MediatorLiveData<RecipesListModel>()

        mObservableRecipes.addSource(dao.getRecipes(),
                { recipeEntities ->
                    if (recipeEntities != null) {
                        val model = RecipesListModel(false, recipeEntities)
                        mObservableRecipes.postValue(model)
                    }
                })
    }

    fun getRecipes(): LiveData<RecipesListModel> {
        return mObservableRecipes
    }

    //todo return boolean to confirm? reactive app?
    fun addRecipe(recipe: Recipe) {
        mDao.saveRecipe(recipe)
    }
}