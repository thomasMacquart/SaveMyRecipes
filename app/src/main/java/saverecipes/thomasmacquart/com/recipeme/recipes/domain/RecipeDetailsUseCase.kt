package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import io.reactivex.Single
import saverecipes.thomasmacquart.com.recipeme.recipes.model.RecipeDetailsUiModel

open class RecipeDetailsUseCase(private val recipeRepo: RecipeRepo) {

    open fun getRecipe(id : Long) : Single<RecipeDetailsUiModel> {
        return recipeRepo.getRecipe(id).map { it -> RecipeDetailsUiModel(it.name, it.type, it.description, it.imageUri) }
    }
}