package saverecipes.thomasmacquart.com.recipeme.core.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import saverecipes.thomasmacquart.com.recipeme.RecipeMeApplication
import javax.inject.Singleton

/**
 * Created by thomas.macquart on 12/02/2018.
 */

@Module
abstract class AppModule(val app : RecipeMeApplication) {
    @Binds
    abstract fun provideContext(app : RecipeMeApplication) : Context
}