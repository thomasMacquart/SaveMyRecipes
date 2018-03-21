package saverecipes.thomasmacquart.com.recipeme.recipes.di

import dagger.Module
import dagger.Provides
import saverecipes.thomasmacquart.com.recipeme.recipes.data.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.viewmodel.CreateRecipeViewModel
import saverecipes.thomasmacquart.com.recipeme.recipes.viewmodel.RecipeListViewModelFactory

/**
 * Created by thomas.macquart on 21/03/2018.
 */
@Module
class CreateRecipeActivityModule {
    @Provides
    fun provideModelView(repo : RecipeRepo): CreateRecipeViewModel = RecipeListViewModelFactory(repo).create(CreateRecipeViewModel::class.java)
}