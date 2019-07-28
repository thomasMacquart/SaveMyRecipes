package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import io.reactivex.Single
import saverecipes.thomasmacquart.com.recipeme.recipes.data.DailyRecipeService
import javax.inject.Inject

class DailyRecipesRepo @Inject constructor(private val dailyRecipeService: DailyRecipeService) : DailyRecipesRepoContract{
    override fun getDailyRecipes(): Single<List<Recipe>> {
        return dailyRecipeService.getDailyRecipes().map { list ->
             list.mapNotNull { it -> Recipe(it.title, it.type, it.description) }
        }
    }
}