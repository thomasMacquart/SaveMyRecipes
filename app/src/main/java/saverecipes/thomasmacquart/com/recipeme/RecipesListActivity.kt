package saverecipes.thomasmacquart.com.recipeme

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.View

class RecipesListActivity : Activity(), View.OnClickListener {

    lateinit var createRecipeButton : FloatingActionButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes_list_activity)

        createRecipeButton = findViewById(R.id.create_recipe_button);
        createRecipeButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val viewId : Int = p0!!.id

        when (viewId) {
            R.id.create_recipe_button -> goToCreateRecipe()
        }
    }

    private fun goToCreateRecipe() {
        startActivity(this.UserDetailIntent())
    }
}
