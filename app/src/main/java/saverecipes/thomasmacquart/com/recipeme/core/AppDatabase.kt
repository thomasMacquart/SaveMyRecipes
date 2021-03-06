package saverecipes.thomasmacquart.com.recipeme.core

import androidx.room.Database
import androidx.room.RoomDatabase
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.dao.RecipeDao

/**
 * Created by thomas.macquart on 06/01/2018.
 */
@Database(entities = arrayOf(Recipe::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun RecipeDao() : RecipeDao
}