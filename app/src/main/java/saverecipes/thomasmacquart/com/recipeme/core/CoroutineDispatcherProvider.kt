package saverecipes.thomasmacquart.com.recipeme.core

import kotlinx.coroutines.CoroutineDispatcher

data class CoroutinesDispatcherProvider(
        val main: CoroutineDispatcher,
        val computation: CoroutineDispatcher,
        val io: CoroutineDispatcher
)