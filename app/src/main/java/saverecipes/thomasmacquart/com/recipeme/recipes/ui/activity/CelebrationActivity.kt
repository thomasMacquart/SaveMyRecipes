package saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.util.Log
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.GifDomain
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.CelebrationViewModel
import javax.inject.Inject

fun Context.CelebrationIntent(): Intent {
    return Intent(this, CelebrationActivity::class.java)
}

class CelebrationActivity : AppCompatActivity(){

    @Inject
    lateinit var activityDispatchingAndroidInjector : DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var factory : ViewModelFactory<CelebrationViewModel>

    lateinit var model : CelebrationViewModel

    fun createViewModel(): CelebrationViewModel {
        return ViewModelProviders.of(this, factory)
                .get(CelebrationViewModel::class.java!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.celebration_activity)

        model = createViewModel()


        doRequest()
    }

    private fun doRequest() {
        model.liveData.observe(this, Observer { response -> Log.d("thomas", "response = ${response.toString()}")})
    }
}