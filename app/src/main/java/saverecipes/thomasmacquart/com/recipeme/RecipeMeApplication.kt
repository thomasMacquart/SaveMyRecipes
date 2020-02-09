package saverecipes.thomasmacquart.com.recipeme

import android.app.Application
import saverecipes.thomasmacquart.com.recipeme.core.di.DaggerAppComponent

/**
 * Created by thomas.macquart on 01/11/2017.
 */
class RecipeMeApplication : Application() {

    val appComponent by lazy { DaggerAppComponent.factory().create(applicationContext)}
}