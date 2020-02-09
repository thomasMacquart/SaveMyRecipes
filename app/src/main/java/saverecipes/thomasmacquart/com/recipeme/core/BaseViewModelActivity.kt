package saverecipes.thomasmacquart.com.recipeme.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import saverecipes.thomasmacquart.com.recipeme.RecipeMeApplication
import javax.inject.Inject

abstract class BaseViewModelActivity<VM : ViewModel> : AppCompatActivity() {

    @Inject
    lateinit var factory : ViewModelFactory<VM>

    val viewModel : VM by lazy { createViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        //(applicationContext as RecipeMeApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    abstract fun createViewModel() : VM
}