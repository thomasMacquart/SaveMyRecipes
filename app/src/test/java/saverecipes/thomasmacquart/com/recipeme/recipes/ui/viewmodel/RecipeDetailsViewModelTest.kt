package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel

import androidx.lifecycle.Observer
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import saverecipes.thomasmacquart.com.recipeme.recipes.InstantTaskExecutorExtension
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.RecipeDetailsUseCase
import saverecipes.thomasmacquart.com.recipeme.recipes.model.RecipeDetailsUiModel

@ExtendWith(InstantTaskExecutorExtension::class) //TODO : use rule instead when supported by junit5
class RecipeDetailsViewModelTest {


    @Mock
    private lateinit var useCase: RecipeDetailsUseCase
    @Mock
    private lateinit var observerState: Observer<RecipeDetailsState>


    private lateinit var viewModel: RecipeDetailsViewModel

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = RecipeDetailsViewModel(useCase, Schedulers.trampoline(), Schedulers.trampoline())
    }


    @Test
    fun getRecipe() {
        val uiModel = RecipeDetailsUiModel("test", "test", "test")

        Mockito.`when`(useCase.getRecipe(ArgumentMatchers.anyLong())).thenReturn(Single.just(uiModel))
        viewModel.recipeObservableUi.observeForever { observerState }
        viewModel.getRecipe(1)
        verify(useCase).getRecipe(1)

        /*val argumentCaptor = ArgumentCaptor.forClass(RecipeDetailsState::class.java)
        val expectedSucessState = RecipeDetailsState.OnSuccess(uiModel)
        argumentCaptor.run {
            verify(observerState, times(2)).onChanged(capture())
            //assertEquals(expectedSucessState, value)
        }*/

        verify(observerState).onChanged(RecipeDetailsState.OnSuccess(uiModel))
    }


}