package saverecipes.thomasmacquart.com.recipeme.core

import dagger.Module
import dagger.android.ContributesAndroidInjector
import saverecipes.thomasmacquart.com.recipeme.recipes.RecipesListActivityModule
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.RecipesListActivity


/**
 * Created by thomas.macquart on 07/03/2018.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(RecipesListActivityModule::class))
    internal abstract fun bindRecipesListActivity(): RecipesListActivity
}