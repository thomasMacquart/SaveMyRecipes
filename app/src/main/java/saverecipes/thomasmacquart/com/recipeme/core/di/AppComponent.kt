package saverecipes.thomasmacquart.com.recipeme.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import saverecipes.thomasmacquart.com.recipeme.recipes.di.RecipesComponent
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity.MainNavigationActivity
import javax.inject.Singleton

/**
 * Created by thomas.macquart on 12/02/2018.
 */

@Singleton
@Component(modules = [NetworkModule::class, AppSubComponents::class])
interface AppComponent  {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }

    fun recipesComponent(): RecipesComponent.Factory

    fun inject(activity: MainNavigationActivity)
}