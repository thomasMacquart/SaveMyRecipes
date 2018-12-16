package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import io.reactivex.Single
import saverecipes.thomasmacquart.com.recipeme.recipes.model.RecipeDetailsUiModel

interface IRecipeDetailsUserCase {
    fun getRecipe(id : Long) : Single<RecipeDetailsUiModel>
}