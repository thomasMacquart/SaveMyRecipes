package saverecipes.thomasmacquart.com.recipeme.recipes.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.create_recipe_activity.*
import saverecipes.thomasmacquart.com.recipeme.BuildConfig
import saverecipes.thomasmacquart.com.recipeme.R
import saverecipes.thomasmacquart.com.recipeme.core.ViewModelFactory
import saverecipes.thomasmacquart.com.recipeme.core.exhaustive
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.CreateRecipeState
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.CreateRecipeViewModel
import saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.CreateRecipesIntentions
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


/**
 * Created by thomas.macquart on 21/03/2018.
 */
class CreateRecipeActivity : AppCompatActivity(), HasActivityInjector {

    companion object {
        private const val REQUEST_TAKE_PHOTO = 1

        fun getStartIntent(context: Context) : Intent {
            return Intent(context, CreateRecipeActivity::class.java)
        }
    }

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var factory: ViewModelFactory<CreateRecipeViewModel>

    lateinit var model: CreateRecipeViewModel

    private var mCurrentPhotoPath: String? = null

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

        take_photo.setOnClickListener { takePhoto() }

        validate_recipe_button.setOnClickListener {
            model.sendIntention(CreateRecipesIntentions.CreateRecipe(Recipe(recipe_title_input.text.toString(), recipe_desciption_input.text.toString(), recipe_type_spinner.selectedItem.toString())))
            startActivity(SuccessActivity.Companion.getStartIntent(this))
            setResult(Activity.RESULT_OK)
            finish()
        }

        observe()
    }

    private fun observe() {
        model.uiObservable.observe(this, androidx.lifecycle.Observer {
            when(it) {
                is CreateRecipeState.ShowError -> view_state.showError(it.error) {}
            }.exhaustive
        })
    }

    private fun showImage(uri : String) {
        val requestOption = RequestOptions()
                .placeholder(R.drawable.recipe_empty_state_icon).centerCrop()

        Glide.with(this).load(uri)
                .apply(requestOption)
                .into(select_image)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {

            REQUEST_TAKE_PHOTO -> {
                if (resultCode == RESULT_OK) {
                    //val imageBitmap = data?.extras?.get("data") as Bitmap
                    mCurrentPhotoPath?.let { showImage(it) }
                }

            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
                "JPEG_${timeStamp}_", /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
          mCurrentPhotoPath = absolutePath
        }
    }

    private fun takePhoto() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    view_state.showError("something went wrong") {}
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                            this,
                            "${BuildConfig.APPLICATION_ID}.provider",
                            it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }


    fun createViewModel(): CreateRecipeViewModel {
        return ViewModelProviders.of(this, factory)
                .get(CreateRecipeViewModel::class.java)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}