package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import saverecipes.thomasmacquart.com.recipeme.core.CoroutinesDispatcherProvider
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import javax.inject.Inject

/**
 * Created by thomas.macquart on 21/03/2018.
 */
//todo interface + notify view when update finished
class CreateRecipeViewModel @Inject constructor(repo : RecipeRepo) : ViewModel(){


    private val repository : RecipeRepo = repo

    fun createRecipe(recipe : Recipe)  {
        repository.addRecipe(recipe)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}