package saverecipes.thomasmacquart.com.recipeme.recipes.di

import dagger.Module
import dagger.Provides
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.GifRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.CelebrationViewModel

@Module
class CelebrationActivityModule {
    @Provides
    fun provideModelView(): ViewModelFactory<CelebrationViewModel> = ViewModelFactory(CelebrationViewModel())
}