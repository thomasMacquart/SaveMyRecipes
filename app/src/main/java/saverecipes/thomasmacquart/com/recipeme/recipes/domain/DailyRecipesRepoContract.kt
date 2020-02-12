package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import saverecipes.thomasmacquart.com.recipeme.core.utils.AsyncResponse

interface DailyRecipesRepoContract {
    suspend fun getDailyRecipes() : AsyncResponse<List<Recipe>>
}