package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import io.reactivex.Single
import saverecipes.thomasmacquart.com.recipeme.recipes.model.RecipeDetailsUiModel

class RecipeDetailsUseCase(private val recipeRepo: RecipeRepo) {

    fun getRecipe(id : Long) : Single<RecipeDetailsUiModel> {
        return recipeRepo.getRecipe(id).map { it -> RecipeDetailsUiModel(it.name, it.type, it.description) }
    }
}