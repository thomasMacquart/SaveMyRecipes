package saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity

import android.app.Activity
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
import android.R.attr.data
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


/**
 * Created by thomas.macquart on 21/03/2018.
 */

fun Context.UserDetailIntent(): Intent {
    return Intent(this, CreateRecipeActivity::class.java)
}

class CreateRecipeActivity : AppCompatActivity(), HasActivityInjector {

    companion object {
        private const val REQUEST_SELECT_IMAGE = 0
    }

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

        pick_image.setOnClickListener { selectImageInGallery() }

        validate_recipe_button.setOnClickListener {
            model.createRecipe(Recipe(recipe_title_input.text.toString(), recipe_desciption_input.text.toString(), recipe_type_spinner.selectedItem.toString()))
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            REQUEST_SELECT_IMAGE -> {
                if (resultCode === Activity.RESULT_OK) {
                    if (data != null && data.data != null) {

                        val uri = data.data
                        model.setImageUri(uri = uri)
                        val requestOption = RequestOptions()
                                .placeholder(R.drawable.recipe_empty_state_icon).centerCrop()

                        Glide.with(this).load(uri)
                                .apply(requestOption)
                                .into(select_image)
                    }
                }
            }
        }
    }

    private fun selectImageInGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE)
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