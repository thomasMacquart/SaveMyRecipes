package saverecipes.thomasmacquart.com.recipeme

import android.app.Application
import android.arch.persistence.room.Room
import saverecipes.thomasmacquart.com.recipeme.core.AppComponent
import saverecipes.thomasmacquart.com.recipeme.core.AppModule
import saverecipes.thomasmacquart.com.recipeme.core.DaggerAppComponent

/**
 * Created by thomas.macquart on 01/11/2017.
 */
class RecipeMeApplication : Application() {

    companion object {
        lateinit var database: AppDatabase
            private set
        lateinit var instance: RecipeMeApplication
            private set
    }

    lateinit var recipeComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        //component.inject(this)
        database = Room.databaseBuilder(this,
                AppDatabase::class.java, "recipeme-db").allowMainThreadQueries().build()
        instance = this
        recipeComponent = initDagger(this)
    }

    private fun initDagger(app: RecipeMeApplication): AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .build()
}