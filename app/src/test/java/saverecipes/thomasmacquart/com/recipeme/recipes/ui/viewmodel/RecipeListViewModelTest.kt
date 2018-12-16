package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import saverecipes.thomasmacquart.com.recipeme.recipes.RxSchedulerRule
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.testObserver


/**
 * Created by thomas.macquart on 08/03/2018.
 */
class RecipeListViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @Mock
    private lateinit var repo : RecipeRepo

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
            Mockito.`when`(repo.getRecipes()).thenReturn(Flowable.just(listOf()))
            val recipeListStatus = viewModel.recipes.testObserver()

            viewModel.loadRecipes()
            verify(repo.getRecipes(), times(1))

            assertEquals(listOf(RecipeListState.LoadingState, RecipeListState.EmptyState), recipeListStatus.observedValues)
        }

        @Test
        fun `Given list is not empty return success state`() {
            val list = mutableListOf<Recipe>()
            val recipe = mock(Recipe::class.java)
            list.add(recipe)
            Mockito.`when`(repo.getRecipes()).thenReturn(Flowable.just(list))
            val recipeListStatus = viewModel.recipes.testObserver()

            viewModel.loadRecipes()
            verify(repo.getRecipes(), times(1))

            assertEquals(listOf(RecipeListState.LoadingState, RecipeListState.SuccessState(list)), recipeListStatus.observedValues)
        }

        @Test
        fun `Given repo return error return error state`() {
            Mockito.`when`(repo.getRecipes()).thenReturn(Flowable.error(Throwable("oops")))
            val recipeListStatus = viewModel.recipes.testObserver()

            viewModel.loadRecipes()
            verify(repo.getRecipes(), times(1))

            assertEquals(listOf(RecipeListState.LoadingState, RecipeListState.ErrorState(ArgumentMatchers.anyString())), recipeListStatus.observedValues)
        }
    }
}