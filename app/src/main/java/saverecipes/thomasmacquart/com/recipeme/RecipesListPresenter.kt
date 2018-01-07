package saverecipes.thomasmacquart.com.recipeme

/**
 * Created by thomas.macquart on 07/01/2018.
 */
class RecipesListPresenter constructor(val recipeDao : RecipeDao) : BasePresenter<RecipeListView>() {

    fun getRecipes() {
        view?.onDataFetched(recipeDao.getRecipes())
    }
}