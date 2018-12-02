package saverecipes.thomasmacquart.com.recipeme.recipes.di

import dagger.Module
import dagger.Provides
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepoImpl
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.CreateRecipeViewModel

/**
 * Created by thomas.macquart on 21/03/2018.
 */
@Module
class CreateRecipeActivityModule {
    @Provides
    fun provideModelView(repoImpl : RecipeRepoImpl): ViewModelFactory<CreateRecipeViewModel>
            = ViewModelFactory(CreateRecipeViewModel(repoImpl))
}