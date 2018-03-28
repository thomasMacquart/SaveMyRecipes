package saverecipes.thomasmacquart.com.recipeme.core.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import saverecipes.thomasmacquart.com.recipeme.recipes.di.CreateRecipeActivityModule
import saverecipes.thomasmacquart.com.recipeme.recipes.di.RecipesListActivityModule
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity.CreateRecipeActivity
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity.RecipesListActivity


/**
 * Created by thomas.macquart on 07/03/2018.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(RecipesListActivityModule::class))
    abstract fun bindRecipesListActivity(): RecipesListActivity

    @ContributesAndroidInjector(modules = arrayOf(CreateRecipeActivityModule::class))
    abstract fun bindCreateRecipeActivity(): CreateRecipeActivity
}