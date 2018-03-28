package saverecipes.thomasmacquart.com.recipeme

import android.app.Activity
import android.arch.lifecycle.*
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onIdle
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity.RecipesListActivity
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.RecipeListViewModel
import javax.inject.Provider


/**
 * Created by thomas.macquart on 27/03/2018.
 */
@RunWith(AndroidJUnit4::class)
class RecipesListActivityTest {




        @get:Rule
        val activityTestRule = object : ActivityTestRule<RecipesListActivity>(RecipesListActivity::class.java, true, true) {
            override fun beforeActivityLaunched() {
                super.beforeActivityLaunched()

                var mockViewModel : RecipeListViewModel = mock(RecipeListViewModel::class.java)
                var liveData = MutableLiveData<List<Recipe>>()

                `when`(mockViewModel.getRecipes()).thenReturn(liveData)

                var list : List<Recipe> = mutableListOf(Recipe("testTitle", "test", "type"))
                liveData.value= list

                val myApp = InstrumentationRegistry.getTargetContext().applicationContext as RecipeMeApplication
                myApp.activityDispatchingAndroidInjector = createFakeActivityInjector<RecipesListActivity> {
                    factory = ViewModelFactory(mockViewModel)
                }
            }
        }

    @Test
    //todo run in ui thread only
    fun screenTest() {
        onView(withId(R.id.create_recipe_button)).perform(click())
    }

    inline fun <reified T : Activity> createFakeActivityInjector(crossinline block : T.() -> Unit)
            : DispatchingAndroidInjector<Activity> {
        val injector = AndroidInjector<Activity> { instance ->
            if (instance is T) {
                instance.block()
            }
        }
        val factory = AndroidInjector.Factory<Activity> { injector }
        val map = mapOf(Pair<Class <out Activity>, javax.inject.Provider<AndroidInjector.Factory<out Activity>>>(T::class.java, Provider { factory }))
        return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(map)
    }
}