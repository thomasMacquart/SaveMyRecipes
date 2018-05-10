package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import android.arch.lifecycle.ViewModel
import saverecipes.thomasmacquart.com.recipeme.core.DataHandler
import saverecipes.thomasmacquart.com.recipeme.core.RetrofitLiveData
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.GifDomain

class CelebrationViewModel() : ViewModel()  {
    val liveData : RetrofitLiveData<GifDomain> = DataHandler.INSTANCE.gifHandler.getGif()
}