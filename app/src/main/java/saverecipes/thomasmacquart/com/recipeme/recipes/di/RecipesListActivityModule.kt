package saverecipes.thomasmacquart.com.recipeme.recipes.di

import dagger.Module
import dagger.Provides
import saverecipes.thomasmacquart.com.recipeme.recipes.data.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.viewmodel.RecipeListViewModel
import saverecipes.thomasmacquart.com.recipeme.recipes.viewmodel.RecipeListViewModelFactory

/**
 * Created by thomas.macquart on 13/02/2018.
 */
@Module
class RecipesListActivityModule {
    @Provides
    fun provideModelView(repo : RecipeRepo): RecipeListViewModel = RecipeListViewModelFactory(repo).create(RecipeListViewModel::class.java)

}