package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import saverecipes.thomasmacquart.com.recipeme.recipes.data.DailyRecipe
import saverecipes.thomasmacquart.com.recipeme.recipes.data.DailyRecipeService
import saverecipes.thomasmacquart.com.recipeme.recipes.utils.CoroutinesTestRule

internal class DailyRecipesRepoTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val service : DailyRecipeService = mock()

    private lateinit var repo : DailyRecipesRepo

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        repo = DailyRecipesRepo(service)
    }

    @Test
    @Ignore
    fun `given dao return a list with recipes`() {
        coroutinesTestRule.runBlockingTest {
            val list = mutableListOf<DailyRecipe>()
            val dailyRecipe = DailyRecipe("test", "test", "test")
            list.add(dailyRecipe)

            val response = mock<Response<List<DailyRecipe>>>()
            whenever(response.isSuccessful).thenReturn(true)

            Mockito.`when`(service.getDailyRecipes()).thenReturn(response)

            val resultList = mutableListOf<Recipe>()
            val recipe = Recipe("test", "test", "test")
            resultList.add(recipe)

            assertEquals(resultList, repo.getDailyRecipes())
        }
    }
}