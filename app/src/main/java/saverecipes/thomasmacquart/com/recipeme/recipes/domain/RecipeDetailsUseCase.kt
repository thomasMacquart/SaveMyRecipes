package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import io.reactivex.Single
import saverecipes.thomasmacquart.com.recipeme.recipes.model.RecipeDetailsModelView

class RecipeDetailsUseCase(val recipeRepo: RecipeRepo) {

    fun execute(id : Long) : Single<RecipeDetailsModelView> {
        return recipeRepo.getRecipe(id).map { it -> RecipeDetailsModelView(it.name) }
    }
}