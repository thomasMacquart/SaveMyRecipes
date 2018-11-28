package saverecipes.thomasmacquart.com.recipeme.recipes.di

import dagger.Module
import dagger.Provides
import saverecipes.thomasmacquart.com.recipeme.core.CoroutinesDispatcherProvider
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListViewModel

/**
 * Created by thomas.macquart on 13/02/2018.
 */
@Module
class RecipesListActivityModule {
    @Provides
    fun provideModelView(repo : RecipeRepo): ViewModelFactory<RecipeListViewModel>
            = ViewModelFactory(RecipeListViewModel(repo))

}