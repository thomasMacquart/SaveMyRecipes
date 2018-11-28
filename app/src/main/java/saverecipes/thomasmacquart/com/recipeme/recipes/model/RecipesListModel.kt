package saverecipes.thomasmacquart.com.recipeme.recipes.model

import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe

data class RecipesListModel(val isLoading : Boolean, val recipesListResult : List<Recipe>)