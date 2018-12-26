package saverecipes.thomasmacquart.com.recipeme.core.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import saverecipes.thomasmacquart.com.recipeme.recipes.di.DailyRecipeModule
import saverecipes.thomasmacquart.com.recipeme.recipes.di.RecipesListModule
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.fragment.DailyRecipesFragment
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.fragment.RecipesListFragment

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = arrayOf(RecipesListModule::class))
    abstract fun bindRecipeListFragment() : RecipesListFragment

    @ContributesAndroidInjector(modules = arrayOf(DailyRecipeModule::class))
    abstract  fun bindDailyRecipesFragment() : DailyRecipesFragment
}