package saverecipes.thomasmacquart.com.recipeme.recipes.data

import retrofit2.Response
import retrofit2.http.GET

interface DailyRecipeService {
    @GET("v2/5c210bb22e000056001e0cb5")
    suspend fun getDailyRecipes() : Response<List<DailyRecipe>>
}