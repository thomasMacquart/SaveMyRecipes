package saverecipes.thomasmacquart.com.recipeme.core

import android.content.Context
import dagger.Module
import dagger.Provides
import saverecipes.thomasmacquart.com.recipeme.RecipeMeApplication
import javax.inject.Singleton

/**
 * Created by thomas.macquart on 12/02/2018.
 */

@Module
class AppModule(val app : RecipeMeApplication) {
    @Provides
    @Singleton
    fun provideContext() : Context = app
}