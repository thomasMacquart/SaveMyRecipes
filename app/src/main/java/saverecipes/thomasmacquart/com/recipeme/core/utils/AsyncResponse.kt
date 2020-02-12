package saverecipes.thomasmacquart.com.recipeme.core.utils

sealed class AsyncResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : AsyncResponse<T>()
    data class Failed(val exception: Exception) : AsyncResponse<Nothing>()
}