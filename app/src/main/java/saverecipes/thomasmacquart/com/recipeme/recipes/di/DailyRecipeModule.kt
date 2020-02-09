package saverecipes.thomasmacquart.com.recipeme.recipes.di

import dagger.Module
import dagger.Provides
import saverecipes.thomasmacquart.com.recipeme.recipes.data.DailyRecipeService
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.DailyRecipesRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.DailyRecipesViewModel

@Module
class DailyRecipeModule {


    @Provides
    fun provideDailyRecipesRepo(dailyRecipeService: DailyRecipeService) : DailyRecipesRepo = DailyRecipesRepo(dailyRecipeService)

    @Provides
    fun provideDailyRecipeViewModelFactory(dailyRecipesRepo: DailyRecipesRepo) : DailyRecipesViewModel.Factory =
            DailyRecipesViewModel.Factory(dailyRecipesRepo)
}