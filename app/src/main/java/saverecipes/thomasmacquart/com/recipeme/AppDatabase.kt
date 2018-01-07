package saverecipes.thomasmacquart.com.recipeme

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by thomas.macquart on 06/01/2018.
 */
@Database(entities = arrayOf(Recipe::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun RecipeDao() : RecipeDao
}