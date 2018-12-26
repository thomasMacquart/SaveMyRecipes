package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import io.reactivex.Single

interface DailyRecipesRepoContract {
    fun getDailyRecipes() : Single<List<Recipe>>
}