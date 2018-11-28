package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.reactivex.Completable
import io.reactivex.Single
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
    }

    /*suspend fun getRecipes(): LiveData<RecipesListModel> {
        mObservableRecipes.addSource(mDao.getRecipes()
        ) { recipeEntities ->
            if (recipeEntities != null) {
                val model = RecipesListModel(false, recipeEntities)
                mObservableRecipes.postValue(model)
            }
        }

        return mObservableRecipes;
    }*/

    fun getRecipes(): Single<List<Recipe>> {
        return mDao.getRecipes()
    }

    fun addRecipe(recipe: Recipe) : Completable {
       return Completable.fromAction { mDao.saveRecipe(recipe)}
    }
}