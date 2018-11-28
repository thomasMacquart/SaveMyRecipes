package saverecipes.thomasmacquart.com.recipeme.core.di

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import saverecipes.thomasmacquart.com.recipeme.RecipeMeApplication
import saverecipes.thomasmacquart.com.recipeme.core.AppDatabase
import saverecipes.thomasmacquart.com.recipeme.core.CoroutinesDispatcherProvider
import saverecipes.thomasmacquart.com.recipeme.recipes.dao.RecipeDao
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import javax.inject.Singleton

/**
 * Created by thomas.macquart on 12/02/2018.
 */

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(app : RecipeMeApplication) : Context {
        return app;
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(application: RecipeMeApplication): AppDatabase {
        return Room.databaseBuilder(application,
                AppDatabase::class.java, "recipeme-db").build()
    }

    @Singleton
    @Provides
    fun provideRecipeDao(db : AppDatabase) : RecipeDao {
        return db.RecipeDao()
    }

    @Singleton
    @Provides
    fun provideRecipeRepo(dao : RecipeDao) : RecipeRepo {
        return RecipeRepo(dao)
    }

    @Provides
    fun provideCoroutinesDispatcherProvider() = CoroutinesDispatcherProvider(
            Dispatchers.Main,
            Dispatchers.Default,
            Dispatchers.IO
    )
}