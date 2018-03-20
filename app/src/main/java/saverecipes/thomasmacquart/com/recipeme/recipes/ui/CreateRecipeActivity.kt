package saverecipes.thomasmacquart.com.recipeme.recipes.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.create_recipe_activity.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.RecipeMeApplication
import saverecipes.thomasmacquart.com.recipeme.recipes.data.Recipe


/**
 * Created by thomas.macquart on 26/10/2017.
 */

fun Context.UserDetailIntent(): Intent {
    return Intent(this, CreateRecipeActivity::class.java);
}

class CreateRecipeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_recipe_activity)

        validate_recipe_button.setOnClickListener {
            /*val recipe = Recipe(0, "oeuf au plat", "faites chauffer un oeuf")
            RecipeMeApplication.database?.RecipeDao()?.saveRecipe(recipe);
            finish()*/
        }
    }
}