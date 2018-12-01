package saverecipes.thomasmacquart.com.recipeme.recipes.di

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeDetailsUseCase
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeDetailsViewModel

@Module
class RecipeDetailsModule {

    @Provides
    fun provideUseCase(repo: RecipeRepo) : RecipeDetailsUseCase = RecipeDetailsUseCase(repo)

    @Provides
    fun provideModelView(usecase : RecipeDetailsUseCase): ViewModelFactory<RecipeDetailsViewModel>
            = ViewModelFactory(RecipeDetailsViewModel(usecase, Schedulers.io(), AndroidSchedulers.mainThread()))
}