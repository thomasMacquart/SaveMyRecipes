package saverecipes.thomasmacquart.com.recipeme.core

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import saverecipes.thomasmacquart.com.recipeme.recipes.dao.GifDao
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.GifRepo

class DataHandler private constructor() {

    companion object Singleton {
        val INSTANCE: DataHandler by lazy { DataHandler() }

        const val BASE_URL = "https://api.giphy.com/v1/gifs/"
    }

    val gifHandler: GifRepo

    init {
        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()


        gifHandler = GifRepo(retrofit.create(GifDao::class.java))
    }
}