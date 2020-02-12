package saverecipes.thomasmacquart.com.recipeme.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import saverecipes.thomasmacquart.com.recipeme.recipes.di.CreateRecipeActivityModule
import saverecipes.thomasmacquart.com.recipeme.recipes.di.DailyRecipeModule
import saverecipes.thomasmacquart.com.recipeme.recipes.di.RecipeDetailsModule
import saverecipes.thomasmacquart.com.recipeme.recipes.di.RecipesComponent
import saverecipes.thomasmacquart.com.recipeme.recipes.di.RecipesListModule
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity.CreateRecipeActivity
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity.MainNavigationActivity
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity.RecipeDetailsActivity
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.fragment.DailyRecipesFragment
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.fragment.RecipesListFragment
import javax.inject.Singleton

/**
 * Created by thomas.macquart on 12/02/2018.
 */

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, AppSubComponents::class])
interface AppComponent  {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }

    fun recipesComponent(): RecipesComponent.Factory

    fun inject(activity: MainNavigationActivity)
}