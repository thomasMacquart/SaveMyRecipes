package saverecipes.thomasmacquart.com.recipeme

/**
 * Created by thomas.macquart on 07/01/2018.
 */
interface RecipeListView : MvpView {
    fun onDataFetched(recipes : List<Recipe>)
}