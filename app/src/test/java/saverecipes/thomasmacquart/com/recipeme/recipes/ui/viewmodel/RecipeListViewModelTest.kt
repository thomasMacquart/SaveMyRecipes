package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import saverecipes.thomasmacquart.com.recipeme.core.utils.AsyncResponse
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepoImpl
import saverecipes.thomasmacquart.com.recipeme.recipes.testObserver
import saverecipes.thomasmacquart.com.recipeme.recipes.utils.CoroutinesTestRule
import java.lang.Exception

/**
 * Created by thomas.macquart on 08/03/2018.
 */
class RecipeListViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val repo : RecipeRepoImpl = mock()

    private lateinit var viewModel : RecipeListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = RecipeListViewModel(repo)
    }

    @Nested
    @DisplayName("Test getRecipes")
    inner class TestGetRecipes {
        @Test
        fun `Given list is empty return empty state`() {
            coroutinesTestRule.runBlockingTest {
                whenever(repo.getRecipes()).thenReturn(AsyncResponse.Success(listOf()))
                val recipeListStatus = viewModel.recipes.testObserver()

                viewModel.loadRecipes()
                verify(repo.getRecipes(), times(1))

                assertEquals(listOf(RecipeListState.LoadingState, RecipeListState.EmptyState), recipeListStatus.observedValues)
            }
        }

        @Test
        fun `Given list is not empty return success state`() {
            coroutinesTestRule.runBlockingTest {
                val list = mutableListOf<Recipe>()
                val recipe = mock(Recipe::class.java)
                list.add(recipe)
                whenever(repo.getRecipes()).thenReturn(AsyncResponse.Success(list))
                val recipeListStatus = viewModel.recipes.testObserver()

                viewModel.loadRecipes()
                verify(repo.getRecipes(), times(1))

                assertEquals(listOf(RecipeListState.LoadingState, RecipeListState.SuccessState(list)), recipeListStatus.observedValues)
            }
        }

        @Test
        fun `Given repo return error return error state`() {
            coroutinesTestRule.runBlockingTest {
                whenever(repo.getRecipes()).thenReturn(AsyncResponse.Failed(Exception("oops")))
                val recipeListStatus = viewModel.recipes.testObserver()

                viewModel.loadRecipes()
                verify(repo.getRecipes(), times(1))

                assertEquals(listOf(RecipeListState.LoadingState, RecipeListState.ErrorState(ArgumentMatchers.anyString())), recipeListStatus.observedValues)
            }
        }
    }
}