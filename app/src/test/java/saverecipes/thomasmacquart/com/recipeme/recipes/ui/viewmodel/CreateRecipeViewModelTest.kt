package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import saverecipes.thomasmacquart.com.recipeme.recipes.RxSchedulerRule
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo

class CreateRecipeViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    private val repo  = mock(RecipeRepo::class.java)

    private lateinit var viewModel : CreateRecipeViewModel

    @Before
    fun setUp() {
        viewModel = CreateRecipeViewModel(repo)
    }

    @Test
    fun createRecipeTest() {
        val recipe = Recipe("test", "bla")

        whenever(repo.addRecipe(recipe)).thenReturn(Completable.complete())

        viewModel.sendIntention(CreateRecipesIntentions.CreateRecipe(recipe))
        verify(repo, times(1)).addRecipe(recipe)
    }

}