package saverecipes.thomasmacquart.com.recipeme

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.recipes_list_activity.*

class RecipesListActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes_list_activity)

        create_recipe_button.setOnClickListener {
            goToCreateRecipe()
        }
    }

    private fun goToCreateRecipe() {
        startActivity(this.UserDetailIntent())
    }
}
