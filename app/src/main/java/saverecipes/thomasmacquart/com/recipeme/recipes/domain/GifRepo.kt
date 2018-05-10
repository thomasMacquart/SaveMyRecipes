package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import retrofit2.Call
import saverecipes.thomasmacquart.com.recipeme.core.RetrofitLiveData
import saverecipes.thomasmacquart.com.recipeme.recipes.dao.GifDao

class GifRepo(dao : GifDao) {

    var observable : MutableLiveData<GifDomain> = MutableLiveData()

    //todo inject this
    val apiService = dao

    /*fun getGif() : LiveData<GifDomain> {
        val response = apiService.search("Xqy12gsWpZowPnNyI5UH8DwmkW0OO7tb", "celebration", 1, "G", "en").execute()
        if (response.isSuccessful) {
            if (response.body() != null) {
                var gif: GifDomain = response.body()!!
                observable.value = gif
                return observable
            }
        }

        return observable
    }*/

    fun getGif() : RetrofitLiveData<GifDomain> {
        return RetrofitLiveData(apiService.search("", "celebration", 1, "G", "en"))
    }

}