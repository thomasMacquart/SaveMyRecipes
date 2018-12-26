package saverecipes.thomasmacquart.com.recipeme.core.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import saverecipes.thomasmacquart.com.recipeme.RecipeMeApplication
import javax.inject.Singleton

/**
 * Created by thomas.macquart on 12/02/2018.
 */

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class, FragmentBuilder::class, NetworkModule::class))
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(app: RecipeMeApplication): Builder
        fun build(): AppComponent
    }

    override fun inject(instance: DaggerApplication)

    fun inject(app: RecipeMeApplication)
}