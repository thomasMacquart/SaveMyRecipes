package saverecipes.thomasmacquart.com.recipeme.recipes.di

import dagger.Module
import dagger.Provides
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepoImpl
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeDetailsViewModel

@Module
class RecipeDetailsModule {


    @Provides
    fun provideModelView(repo : RecipeRepoImpl): ViewModelFactory<RecipeDetailsViewModel>
            = ViewModelFactory(RecipeDetailsViewModel(repo))
}