package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import saverecipes.thomasmacquart.com.recipeme.core.utils.AsyncResponse
import saverecipes.thomasmacquart.com.recipeme.core.utils.safeApiCall
import saverecipes.thomasmacquart.com.recipeme.recipes.data.DailyRecipeService
import javax.inject.Inject

class DailyRecipesRepo @Inject constructor(private val dailyRecipeService: DailyRecipeService) : DailyRecipesRepoContract {

    override suspend fun getDailyRecipes(): AsyncResponse<List<Recipe>> {
        val result = safeApiCall("get Daily Recipes failed") {
            dailyRecipeService.getDailyRecipes()
        }

        return when (result) {
            is AsyncResponse.Success -> {
                AsyncResponse.Success(result.data.mapNotNull { Recipe(it.title, it.type, it.description) })
            }
            is AsyncResponse.Failed -> result
        }
    }
}