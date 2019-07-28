package saverecipes.thomasmacquart.com.recipeme.recipes.data

import io.reactivex.Single
import retrofit2.http.GET

interface DailyRecipeService {
    @GET("v2/5c210bb22e000056001e0cb5")
    fun getDailyRecipes() : Single<List<DailyRecipe>>
}