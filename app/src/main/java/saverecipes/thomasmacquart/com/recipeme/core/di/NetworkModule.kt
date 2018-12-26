package saverecipes.thomasmacquart.com.recipeme.core.di

import dagger.Module
import dagger.Provides
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import saverecipes.thomasmacquart.com.recipeme.recipes.data.DailyRecipeDao
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import java.text.DateFormat


private const val BASE_URL = "http://www.mocky.io/"

@Module
class NetworkModule {

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) =
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()


    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
                .addInterceptor(logging) //TODO : enable only for debug
                .connectionSpecs(mutableListOf(ConnectionSpec.CLEARTEXT))
                .build()
    }

    @Provides
    fun providesDailyRecipeDao(retrofit: Retrofit) : DailyRecipeDao = retrofit.create(DailyRecipeDao::class.java)
}