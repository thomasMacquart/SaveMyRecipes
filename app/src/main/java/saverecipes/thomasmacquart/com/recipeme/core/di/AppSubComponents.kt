package saverecipes.thomasmacquart.com.recipeme.core.di

import dagger.Module
import saverecipes.thomasmacquart.com.recipeme.recipes.di.RecipesComponent

@Module(subcomponents = [RecipesComponent::class])
class AppSubComponents {
}