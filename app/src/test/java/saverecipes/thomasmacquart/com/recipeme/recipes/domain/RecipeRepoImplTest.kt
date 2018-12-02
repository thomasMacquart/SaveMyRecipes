package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.jupiter.api.*
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import saverecipes.thomasmacquart.com.recipeme.recipes.dao.RecipeDao

internal class RecipeRepoImplTest {

    @Mock
    private lateinit var dao : RecipeDao

    private val repoImpl : RecipeRepoImpl by lazy { RecipeRepoImpl(dao) }

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Nested
    @DisplayName("Test get recipe")
    inner class TestGetRecipe {
        @Test
        fun `given dao return complete`() {
            val result = Recipe("test", "test", "test")
            Mockito.`when`(dao.findRecipeById(anyLong())).thenReturn(Single.just(result))

            repoImpl.getRecipe(1).test().assertComplete()
        }

        @Test
        fun `given dao return error`() {
            val result = Throwable("error")
            Mockito.`when`(dao.findRecipeById(anyLong())).thenReturn(Single.error(result))

            repoImpl.getRecipe(1).test().assertError(result)
        }
    }

    @Nested
    @DisplayName("Test add recipe")
    @Disabled //TODO : need to inject the completable to test this
    inner class TestAddRecipe {
        @Test
        fun addRecipe() {
            val result = Recipe("test", "test", "test")
            repoImpl.addRecipe(result)
            verify(dao).saveRecipe(result)
        }
    }

    @Nested
    @DisplayName("Test get recipes")
    inner class TestGetRecipes {
        @Test
        fun `Given dao return complete`() {
            val recipe = Recipe("test", "test", "test")
            val result = mutableListOf<Recipe>()
            result.add(recipe)
            Mockito.`when`(dao.getRecipes()).thenReturn(Flowable.just(result))

            repoImpl.getRecipes().test().assertComplete()
        }

        @Test
        fun `Given dao return error`() {
            val result = Throwable("error")
            Mockito.`when`(dao.getRecipes()).thenReturn(Flowable.error(result))

            repoImpl.getRecipes().test().assertError(result)
        }
    }
}