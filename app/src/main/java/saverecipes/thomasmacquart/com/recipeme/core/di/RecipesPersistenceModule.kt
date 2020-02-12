package saverecipes.thomasmacquart.com.recipeme.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import saverecipes.thomasmacquart.com.recipeme.core.AppDatabase
import saverecipes.thomasmacquart.com.recipeme.recipes.dao.RecipeDao
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepoImpl

/**
 * Created by thomas.macquart on 12/02/2018.
 */

@Module
class RecipesPersistenceModule {

    @Provides
    fun providesRoomDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context,
                AppDatabase::class.java, "recipeme-db").build()
    }

    @Provides
    fun provideRecipeDao(db : AppDatabase) : RecipeDao {
        return db.RecipeDao()
    }

    @Provides
    fun provideRecipeRepo(dao : RecipeDao) : RecipeRepoImpl {
        return RecipeRepoImpl(dao)
    }
}