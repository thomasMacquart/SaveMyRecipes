package saverecipes.thomasmacquart.com.recipeme.recipes

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.RecipesListActivity
import saverecipes.thomasmacquart.com.recipeme.recipes.viewmodel.RecipeListViewModel

/**
 * Created by thomas.macquart on 13/02/2018.
 */
@Module
class RecipesListActivityModule {
    @Provides
    fun provideModelView(activity : RecipesListActivity): RecipeListViewModel = ViewModelProviders.of(activity).get(RecipeListViewModel::class.java!!)

}