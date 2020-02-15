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
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import saverecipes.thomasmacquart.com.recipeme.core.utils.AsyncResponse
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.DailyRecipesRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.testObserver
import saverecipes.thomasmacquart.com.recipeme.recipes.utils.CoroutinesTestRule

internal class DailyRecipesViewModelTest {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val repo : DailyRecipesRepo = mock()

    private lateinit var viewModel : DailyRecipesViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = DailyRecipesViewModel(repo)
    }

    @Nested
    @DisplayName("Test loadRecipes")
    inner class TestLoadRecipes {
        @Test
        fun `Given list is empty return empty state`() {
            coroutinesTestRule.runBlockingTest {
                Mockito.`when`(repo.getDailyRecipes()).thenReturn(AsyncResponse.Success(listOf()))
                val recipeListStatus = viewModel.recipesObservable.testObserver()

                viewModel.loadDailyRecipes()
                Mockito.verify(repo.getDailyRecipes(), Mockito.times(1))

                assertEquals(listOf(DailyRecipesState.Loading, DailyRecipesState.ShowEmpty), recipeListStatus.observedValues)
            }
        }

        @Test
        fun `Given list is not empty return success state`() {
            coroutinesTestRule.runBlockingTest {
                val list = mutableListOf<Recipe>()
                val recipe = Mockito.mock(Recipe::class.java)
                list.add(recipe)
                whenever(repo.getDailyRecipes()).thenReturn(AsyncResponse.Success(list))
                val recipeListStatus = viewModel.recipesObservable.testObserver()

                viewModel.loadDailyRecipes()
                Mockito.verify(repo.getDailyRecipes(), Mockito.times(1))

                assertEquals(listOf(DailyRecipesState.Loading, DailyRecipesState.ShowRecipes(list)), recipeListStatus.observedValues)
            }
        }

        @Test
        fun `Given repo return error return error state`() {
            coroutinesTestRule.runBlockingTest {
                whenever(repo.getDailyRecipes()).thenReturn(AsyncResponse.Failed(Exception("oops")))
                val recipeListStatus = viewModel.recipesObservable.testObserver()

                viewModel.loadDailyRecipes()
                Mockito.verify(repo.getDailyRecipes(), Mockito.times(1))

                assertEquals(listOf(DailyRecipesState.Loading, DailyRecipesState.ShowError(ArgumentMatchers.anyString())), recipeListStatus.observedValues)
            }
        }
    }
}