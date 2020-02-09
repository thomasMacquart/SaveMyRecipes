package saverecipes.thomasmacquart.com.recipeme.core

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import saverecipes.thomasmacquart.com.recipeme.RecipeMeApplication
import javax.inject.Inject

abstract class BaseViewModelFragment<VM : ViewModel>  : Fragment() {

    @Inject
    lateinit var factory : ViewModelFactory<VM>

    val viewModel : VM by lazy { createViewModel() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //(requireActivity().applicationContext as RecipeMeApplication).appComponent.inject(this)
    }

    abstract fun createViewModel() : VM
}