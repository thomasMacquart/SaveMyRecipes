package saverecipes.thomasmacquart.com.recipeme.recipes.di

import dagger.Module
import dagger.Provides
import saverecipes.thomasmacquart.com.recipeme.core.CoroutinesDispatcherProvider
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.CreateRecipeViewModel

/**
 * Created by thomas.macquart on 21/03/2018.
 */
@Module
class CreateRecipeActivityModule {
    @Provides
    fun provideModelView(repo : RecipeRepo, dispatcherProvider: CoroutinesDispatcherProvider): ViewModelFactory<CreateRecipeViewModel>
            = ViewModelFactory(CreateRecipeViewModel(repo, dispatcherProvider))
}