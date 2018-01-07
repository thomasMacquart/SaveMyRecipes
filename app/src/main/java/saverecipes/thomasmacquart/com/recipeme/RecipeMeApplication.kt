package saverecipes.thomasmacquart.com.recipeme

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context

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


    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,
                AppDatabase::class.java, "recipeme-db").allowMainThreadQueries().build()
        instance = this
    }

}