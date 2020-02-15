package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepoImpl
import saverecipes.thomasmacquart.com.recipeme.recipes.utils.CoroutinesTestRule

class CreateRecipeViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val repo  = mock(RecipeRepoImpl::class.java)

    private lateinit var viewModel : CreateRecipeViewModel

    @Before
    fun setUp() {
        viewModel = CreateRecipeViewModel(repo)
    }

    @Test
    fun createRecipeTest() {
        coroutinesTestRule.runBlockingTest {
            val recipe = Recipe("test", "bla")

            viewModel.sendIntention(CreateRecipesIntentions.CreateRecipe(recipe))
            verify(repo, times(1)).addRecipe(recipe)
        }
    }

}