package saverecipes.thomasmacquart.com.recipeme.recipes.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe

/**
 * Created by thomas.macquart on 29/10/2017.
 */
@Dao
interface RecipeDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveRecipe(recipe: Recipe)
    @Query("select * from Recipe")
    suspend fun getRecipes() : List<Recipe>
    @Query("select * from Recipe where id = :id")
    suspend fun findRecipeById(id : Long) : Recipe
    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

}