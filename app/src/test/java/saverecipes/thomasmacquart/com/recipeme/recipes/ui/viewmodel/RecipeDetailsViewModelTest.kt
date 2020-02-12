package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.any
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import saverecipes.thomasmacquart.com.recipeme.recipes.RxSchedulerRule
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepo
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeRepoImpl
import saverecipes.thomasmacquart.com.recipeme.recipes.testObserver

class RecipeDetailsViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    private var repo: RecipeRepoImpl = mock()

    private lateinit var viewModel: RecipeDetailsViewModel

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = RecipeDetailsViewModel(repo)
    }

    @Nested
    @DisplayName("Test getRecipes")
    inner class TestGetRecipe {

        @Test
        fun `Given a recipe then onSuccess state is returned`() {

            val recipe = Recipe("test", "test", "test")

            whenever(repo.getRecipe(1)).thenReturn(Single.just(recipe))
            val recipeDetailsStatus = viewModel.recipeObservableUi.testObserver()
            viewModel.getRecipe(1)
            verify(repo).getRecipe(1)

            assertEquals(listOf(RecipeDetailsState.Loading, RecipeDetailsState.OnSuccess(recipe)), recipeDetailsStatus.observedValues)
        }

        @Test
        fun `Given a error then on error state is return is returned`() {
            whenever(repo.getRecipe(1)).thenReturn(Single.error(Throwable("oops")))
            val recipeDetailsStatus = viewModel.recipeObservableUi.testObserver()
            viewModel.getRecipe(1)
            verify(repo).getRecipe(1)

            assertEquals(listOf(RecipeDetailsState.Loading, RecipeDetailsState.OnError(ArgumentMatchers.anyString())), recipeDetailsStatus.observedValues)
        }
    }


    @Nested
    @DisplayName("Test delete")
    inner class TestDeleteRecipe {

        @Test
        fun `Given repo return success`() {

            viewModel.onDelete()
            verify(repo, times(1)).deleteRecipe(any())
        }
    }

}