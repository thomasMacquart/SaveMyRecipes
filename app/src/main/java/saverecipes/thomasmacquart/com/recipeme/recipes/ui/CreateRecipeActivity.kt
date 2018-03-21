package saverecipes.thomasmacquart.com.recipeme.recipes.ui

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.recipes.viewmodel.CreateRecipeViewModel
import saverecipes.thomasmacquart.com.recipeme.recipes.viewmodel.CreateRecipeViewModelFactory
import javax.inject.Inject

/**
 * Created by thomas.macquart on 21/03/2018.
 */

fun Context.UserDetailIntent(): Intent {
    return Intent(this, CreateRecipeActivity::class.java)
}

class CreateRecipeActivity : AppCompatActivity(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector : DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var factory : CreateRecipeViewModelFactory

    lateinit var model : CreateRecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_recipe_activity)

        model = createViewModel()

    }

    fun createViewModel(): CreateRecipeViewModel {
        return ViewModelProviders.of(this, factory)
                .get(CreateRecipeViewModel::class.java!!)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}