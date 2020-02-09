package saverecipes.thomasmacquart.com.recipeme.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import saverecipes.thomasmacquart.com.recipeme.RecipeMeApplication
import saverecipes.thomasmacquart.com.recipeme.core.AppDatabase
import saverecipes.thomasmacquart.com.recipeme.recipes.dao.RecipeDao
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepoImpl
import javax.inject.Singleton

/**
 * Created by thomas.macquart on 12/02/2018.
 */

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context,
                AppDatabase::class.java, "recipeme-db").build()
    }

    @Singleton
    @Provides
    fun provideRecipeDao(db : AppDatabase) : RecipeDao {
        return db.RecipeDao()
    }

    @Singleton
    @Provides
    fun provideRecipeRepo(dao : RecipeDao) : RecipeRepoImpl {
        return RecipeRepoImpl(dao)
    }
}