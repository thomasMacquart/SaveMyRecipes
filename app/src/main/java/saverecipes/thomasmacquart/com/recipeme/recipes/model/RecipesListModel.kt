package saverecipes.thomasmacquart.com.recipeme.recipes.model

import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import androidx.lifecycle.LiveData

data class RecipesListModel(val isLoading : Boolean, val recipesListResult : List<Recipe>)