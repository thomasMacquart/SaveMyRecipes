package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface RecipeRepo {
    /**
     * return all recipes
     */
    fun getRecipes(): Flowable<List<Recipe>>

    /**
     * save a recipe
     */
    fun addRecipe(recipe: Recipe) : Completable

    /**
     * get recipe by its id
     */
    fun getRecipe(id : Long) : Single<Recipe>

    /**
     * delete recipe from db
     */
    fun deleteRecipe(recipe: Recipe) : Completable
}