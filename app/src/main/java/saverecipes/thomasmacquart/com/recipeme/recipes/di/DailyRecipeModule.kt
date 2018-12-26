package saverecipes.thomasmacquart.com.recipeme.recipes.di

import dagger.Module
import dagger.Provides
import saverecipes.thomasmacquart.com.recipeme.recipes.data.DailyRecipeDao
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.DailyRecipesRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.DailyRecipesViewModel

@Module
class DailyRecipeModule {


    @Provides
    fun provideDailyRecipesRepo(dailyRecipeDao: DailyRecipeDao) : DailyRecipesRepo = DailyRecipesRepo(dailyRecipeDao)

    @Provides
    fun provideDailyRecipeViewModel(dailyRecipesRepo: DailyRecipesRepo) : DailyRecipesViewModel =
            DailyRecipesViewModel(dailyRecipesRepo)
}