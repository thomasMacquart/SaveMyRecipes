package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import io.reactivex.Single
import saverecipes.thomasmacquart.com.recipeme.recipes.model.RecipeDetailsModelView

class RecipeDetailsUseCase(private val recipeRepo: RecipeRepo) {

    fun getRecipe(id : Long) : Single<RecipeDetailsModelView> {
        return recipeRepo.getRecipe(id).map { it -> RecipeDetailsModelView(it.name) }
    }
}