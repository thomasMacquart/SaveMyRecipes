package saverecipes.thomasmacquart.com.recipeme

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.recipes_list_activity.*

class RecipesListActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes_list_activity)

        create_recipe_button.setOnClickListener {
            goToCreateRecipe()
        }

        var recipes : List<Recipe> = RecipeMeApplication.database.RecipeDao().getRecipes()
        var adapter : RecipesListAdapter = RecipesListAdapter(recipes) {

        }
        recipes_list.adapter = adapter
        recipes_list.layoutManager = LinearLayoutManager(this);
        adapter.notifyDataSetChanged()
    }

    private fun goToCreateRecipe() {
        startActivity(this.UserDetailIntent())
    }
}
