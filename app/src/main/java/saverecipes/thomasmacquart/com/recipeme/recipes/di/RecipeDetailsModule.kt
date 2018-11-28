package saverecipes.thomasmacquart.com.recipeme.recipes.di

import dagger.Module
import dagger.Provides
import saverecipes.thomasmacquart.com.recipeme.core.CoroutinesDispatcherProvider
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.CreateRecipeViewModel

@Module
class RecipeDetailsModule {
    @Provides
    fun provideModelView(repo : RecipeRepo): ViewModelFactory<CreateRecipeViewModel>
            = ViewModelFactory(CreateRecipeViewModel(repo))
}