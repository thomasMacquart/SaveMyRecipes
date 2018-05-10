package saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.create_recipe_activity.*
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.CreateRecipeViewModel
import javax.inject.Inject
import android.widget.ArrayAdapter


/**
 * Created by thomas.macquart on 21/03/2018.
 */

fun Context.UserDetailIntent(): Intent {
    return Intent(this, CreateRecipeActivity::class.java)
}

class CreateRecipeActivity : AppCompatActivity(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var factory: ViewModelFactory<CreateRecipeViewModel>

    lateinit var model: CreateRecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_recipe_activity)

        model = createViewModel()

        val adapter = ArrayAdapter.createFromResource(this,
                R.array.meal_types, android.R.layout.simple_spinner_item)
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
// Apply the adapter to the spinner
        recipe_type_spinner.setAdapter(adapter)
        recipe_type_spinner.setSelection(0)

        validate_recipe_button.setOnClickListener {
            model.createRecipe(Recipe(recipe_title_input.text.toString(), recipe_desciption_input.text.toString(), recipe_type_spinner.selectedItem.toString()))
            setResult(Activity.RESULT_OK)
            startActivity(this.CelebrationIntent())
            finish()
        }
    }

    fun createViewModel(): CreateRecipeViewModel {
        return ViewModelProviders.of(this, factory)
                .get(CreateRecipeViewModel::class.java!!)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}