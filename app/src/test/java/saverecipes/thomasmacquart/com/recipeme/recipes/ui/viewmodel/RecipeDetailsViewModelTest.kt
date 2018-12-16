package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import saverecipes.thomasmacquart.com.recipeme.recipes.RxSchedulerRule
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.IRecipeDetailsUserCase
import saverecipes.thomasmacquart.com.recipeme.recipes.model.RecipeDetailsUiModel
import saverecipes.thomasmacquart.com.recipeme.recipes.testObserver

class RecipeDetailsViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    private var useCase: IRecipeDetailsUserCase = mock(IRecipeDetailsUserCase::class.java)

    private lateinit var viewModel: RecipeDetailsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = RecipeDetailsViewModel(useCase)
    }

    @Nested
    @DisplayName("Test getRecipes")
    inner class TestGetRecipe {

        @Test
        fun `Given a recipe then onSuccess state is returned`() {
            viewModel = RecipeDetailsViewModel(useCase)
            val uiModel = RecipeDetailsUiModel("test", "test", "test")

            Mockito.`when`(useCase.getRecipe(1)).thenReturn(Single.just(uiModel))
            val recipeDetailsStatus = viewModel.recipeObservableUi.testObserver()
            viewModel.getRecipe(1)
            verify(useCase).getRecipe(1)

            assertEquals(listOf(RecipeDetailsState.Loading, RecipeDetailsState.OnSuccess(uiModel)), recipeDetailsStatus.observedValues)
        }

        @Test
        fun `Given a error then on error state is return is returned`() {
            viewModel = RecipeDetailsViewModel(useCase)
            val uiModel = RecipeDetailsUiModel("test", "test", "test")

            Mockito.`when`(useCase.getRecipe(1)).thenReturn(Single.error(Throwable("oops")))
            val recipeDetailsStatus = viewModel.recipeObservableUi.testObserver()
            viewModel.getRecipe(1)
            verify(useCase).getRecipe(1)

            assertEquals(listOf(RecipeDetailsState.Loading, RecipeDetailsState.OnError(ArgumentMatchers.anyString())), recipeDetailsStatus.observedValues)
        }
    }




}