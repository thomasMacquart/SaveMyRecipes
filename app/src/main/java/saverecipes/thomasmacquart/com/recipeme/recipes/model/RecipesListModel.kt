package saverecipes.thomasmacquart.com.recipeme.recipes.model

import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import android.arch.lifecycle.LiveData

data class RecipesListModel(val isLoading : Boolean, val recipesListResult : List<Recipe>)