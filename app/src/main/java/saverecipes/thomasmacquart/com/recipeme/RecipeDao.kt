package saverecipes.thomasmacquart.com.recipeme

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

/**
 * Created by thomas.macquart on 29/10/2017.
 */
@Dao
interface RecipeDao {

    @Insert(onConflict = REPLACE)
    fun saveRecipe(recipe: Recipe)
    @Query("select * from Recipe")
    fun getRecipes() : List<Recipe>
}