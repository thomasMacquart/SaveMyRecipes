package saverecipes.thomasmacquart.com.recipeme

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by thomas.macquart on 13/02/2018.
 */
@Module
class RecipeListPresenterModule {
    @Provides
    @Singleton
    fun providePresenter(): RecipesListPresenter = RecipesListPresenter(RecipeMeApplication.database.RecipeDao());
}