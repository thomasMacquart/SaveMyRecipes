package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import saverecipes.thomasmacquart.com.recipeme.recipes.RxSchedulerRule
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.DailyRecipesRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.testObserver

internal class DailyRecipesViewModelTest {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @Mock
    private lateinit var repo : DailyRecipesRepo

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
            Mockito.`when`(repo.getDailyRecipes()).thenReturn(Single.just(listOf()))
            val recipeListStatus = viewModel.recipesObservable.testObserver()

            viewModel.loadDailyRecipes()
            Mockito.verify(repo.getDailyRecipes(), Mockito.times(1))

            assertEquals(listOf(DailyRecipesState.Loading, DailyRecipesState.ShowEmpty), recipeListStatus.observedValues)
        }

        @Test
        fun `Given list is not empty return success state`() {
            val list = mutableListOf<Recipe>()
            val recipe = Mockito.mock(Recipe::class.java)
            list.add(recipe)
            whenever(repo.getDailyRecipes()).thenReturn(Single.just(list))
            val recipeListStatus = viewModel.recipesObservable.testObserver()

            viewModel.loadDailyRecipes()
            Mockito.verify(repo.getDailyRecipes(), Mockito.times(1))

            assertEquals(listOf(DailyRecipesState.Loading, DailyRecipesState.ShowRecipes(list)), recipeListStatus.observedValues)
        }

        @Test
        fun `Given repo return error return error state`() {
            whenever(repo.getDailyRecipes()).thenReturn(Single.error(Throwable("oops")))
            val recipeListStatus = viewModel.recipesObservable.testObserver()

            viewModel.loadDailyRecipes()
            Mockito.verify(repo.getDailyRecipes(), Mockito.times(1))

            assertEquals(listOf(DailyRecipesState.Loading, DailyRecipesState.ShowError(ArgumentMatchers.anyString())), recipeListStatus.observedValues)
        }
    }
}