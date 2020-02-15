package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import saverecipes.thomasmacquart.com.recipeme.core.utils.AsyncResponse

interface RecipeRepo {
    /**
     * return all recipes
     */
    suspend fun getRecipes(): AsyncResponse<List<Recipe>>

    /**
     * save a recipe
     */
    suspend fun addRecipe(recipe: Recipe)

    /**
     * get recipe by its id
     */
    suspend fun getRecipe(id : Long) : AsyncResponse<Recipe>

    /**
     * delete recipe from db
     */
    suspend fun deleteRecipe(recipe: Recipe)
}