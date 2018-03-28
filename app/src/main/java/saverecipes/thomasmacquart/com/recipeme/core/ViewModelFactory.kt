package saverecipes.thomasmacquart.com.recipeme.core

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by thomas.macquart on 27/03/2018.
 */
class ViewModelFactory<V : ViewModel> @Inject
internal constructor(private val mViewModel: V) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return mViewModel as T
    }
}