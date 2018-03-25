package saverecipes.thomasmacquart.com.recipeme.recipes.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import saverecipes.thomasmacquart.com.recipeme.recipes.data.Recipe

/**
 * Created by thomas.macquart on 29/10/2017.
 */
@Dao
interface RecipeDao {

    @Insert(onConflict = REPLACE)
    fun saveRecipe(recipe: Recipe)
    @Query("select * from Recipe")
    fun getRecipes() : LiveData<List<Recipe>>
}