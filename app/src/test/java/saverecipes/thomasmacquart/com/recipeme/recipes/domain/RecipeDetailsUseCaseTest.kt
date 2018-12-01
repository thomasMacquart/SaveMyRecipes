package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class RecipeDetailsUseCaseTest {

    @Mock
    private lateinit var repo: RecipeRepo

    private val useCase: RecipeDetailsUseCase by lazy { RecipeDetailsUseCase(repo) }

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Nested
    @DisplayName("Test get Recipe")
    inner class TestGetRecipe {

        @Test
        fun `given repo return complete`() {
            val result = Recipe("test", "test", "test")
            Mockito.`when`(repo.getRecipe(anyLong())).thenReturn(Single.just(result))

            useCase.getRecipe(1).test().assertComplete()
        }

        @Test
        fun `given repo return error`() {
            val response = Throwable("Error response")
            Mockito.`when`(repo.getRecipe(anyLong())).thenReturn(Single.error(response))

            useCase.getRecipe(1).test().assertError(response)
        }
    }

}