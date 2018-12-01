package saverecipes.thomasmacquart.com.recipeme.recipes.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe

/**
 * Created by thomas.macquart on 29/10/2017.
 */
@Dao
interface RecipeDao {

    @Insert(onConflict = REPLACE)
    fun saveRecipe(recipe: Recipe)
    @Query("select * from Recipe")
    fun getRecipes() : Flowable<List<Recipe>>
    @Query("select * from Recipe where id = :id")
    fun findRecipeById(id : Long) : Single<Recipe>

}