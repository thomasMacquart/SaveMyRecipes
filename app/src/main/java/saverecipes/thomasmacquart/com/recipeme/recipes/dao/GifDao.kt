package saverecipes.thomasmacquart.com.recipeme.recipes.dao

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.GifDomain

interface GifDao {
    @GET("search")
    fun search(@Query("api_key") api_key: String,
               @Query("q") query: String,
               @Query("limit") limit: Int,
               @Query("rating") rating: String,
               @Query("lang") language: String) : Call<GifDomain>

   /* companion object Factory {
        fun create(): GifDao {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.giphy.com/v1/gifs/")
                    .build()

            return retrofit.create(GifDao::class.java)
        }
    }*/
}