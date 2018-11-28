package saverecipes.thomasmacquart.com.recipeme

import android.app.Activity
import android.app.Application
import androidx.room.Room
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

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

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