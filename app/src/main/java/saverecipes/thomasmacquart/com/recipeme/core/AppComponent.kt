package saverecipes.thomasmacquart.com.recipeme.core

import dagger.Component
import saverecipes.thomasmacquart.com.recipeme.RecipeListPresenterModule
import saverecipes.thomasmacquart.com.recipeme.RecipesListActivity
import javax.inject.Singleton

/**
 * Created by thomas.macquart on 12/02/2018.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, RecipeListPresenterModule::class))
interface AppComponent {
    fun inject(target: RecipesListActivity)
}