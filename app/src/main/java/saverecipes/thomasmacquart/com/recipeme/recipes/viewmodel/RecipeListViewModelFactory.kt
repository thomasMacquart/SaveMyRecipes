package saverecipes.thomasmacquart.com.recipeme.recipes.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import saverecipes.thomasmacquart.com.recipeme.recipes.data.RecipeRepo
import javax.inject.Inject


/**
 * Created by thomas.macquart on 08/03/2018.
 */
class RecipeListViewModelFactory @Inject constructor(private val repo: RecipeRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeListViewModel::class.java!!)) {
            return RecipeListViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}