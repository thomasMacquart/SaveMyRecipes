package saverecipes.thomasmacquart.com.recipeme.recipes.di

import dagger.Module
import dagger.Provides
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepoImpl
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListViewModel

/**
 * Created by thomas.macquart on 13/02/2018.
 */
@Module
class RecipesListActivityModule {
    @Provides
    fun provideModelView(repoImpl : RecipeRepoImpl): ViewModelFactory<RecipeListViewModel>
            = ViewModelFactory(RecipeListViewModel(repoImpl))

}