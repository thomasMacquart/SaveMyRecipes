package saverecipes.thomasmacquart.com.recipeme.core

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

abstract class BaseViewModelActivity<VM : ViewModel> : AppCompatActivity(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector : DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var factory : ViewModelFactory<VM>

    lateinit var viewModel : VM

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = createViewModel()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    abstract fun createViewModel() : VM
}