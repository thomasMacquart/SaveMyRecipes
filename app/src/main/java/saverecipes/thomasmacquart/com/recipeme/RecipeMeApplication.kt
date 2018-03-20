package saverecipes.thomasmacquart.com.recipeme

import android.app.Activity
import android.app.Application
import android.arch.persistence.room.Room
import saverecipes.thomasmacquart.com.recipeme.core.AppDatabase
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import saverecipes.thomasmacquart.com.recipeme.core.di.DaggerAppComponent
import javax.inject.Inject

/**
 * Created by thomas.macquart on 01/11/2017.
 */
class RecipeMeApplication : Application(), HasActivityInjector {

    /*companion object {
        lateinit var database: AppDatabase
            private set
    }*/

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        /*database = Room.databaseBuilder(this,
                AppDatabase::class.java, "recipeme-db").allowMainThreadQueries().build()*/

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);


    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}