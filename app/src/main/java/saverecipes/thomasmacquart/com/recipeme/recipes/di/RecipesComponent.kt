package saverecipes.thomasmacquart.com.recipeme.recipes.di

import dagger.Subcomponent
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity.CreateRecipeActivity
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity.RecipeDetailsActivity
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.fragment.DailyRecipesFragment
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.fragment.RecipesListFragment

@Subcomponent(modules = [CreateRecipeActivityModule::class, DailyRecipeModule::class, RecipeDetailsModule::class, RecipesListModule::class])
interface RecipesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create() : RecipesComponent
    }

    fun inject(activity: CreateRecipeActivity)
    fun inject(activity: RecipeDetailsActivity)
    fun inject(fragment: RecipesListFragment)
    fun inject(fragment: DailyRecipesFragment)
}