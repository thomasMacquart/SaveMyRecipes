package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import io.reactivex.Single
import saverecipes.thomasmacquart.com.recipeme.recipes.data.DailyRecipeDao
import javax.inject.Inject

class DailyRecipesRepo @Inject constructor(private val dailyRecipeDao: DailyRecipeDao) : DailyRecipesRepoContract{
    override fun getDailyRecipes(): Single<List<Recipe>> {
        return dailyRecipeDao.getDailyRecipes().map { list ->
             list.mapNotNull { it -> Recipe(it.title, it.type, it.description) }
        }
    }
}