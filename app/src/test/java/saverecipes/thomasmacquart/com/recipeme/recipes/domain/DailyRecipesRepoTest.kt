package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import saverecipes.thomasmacquart.com.recipeme.recipes.data.DailyRecipe
import saverecipes.thomasmacquart.com.recipeme.recipes.data.DailyRecipeService

internal class DailyRecipesRepoTest {

    private val service : DailyRecipeService = mock()

    private lateinit var repo : DailyRecipesRepo

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        repo = DailyRecipesRepo(service)
    }

    @Test
    fun `given dao return a list with recipes`() {
        val list = mutableListOf<DailyRecipe>()
        val dailyRecipe = DailyRecipe("test", "test", "test")
        list.add(dailyRecipe)

        Mockito.`when`(service.getDailyRecipes()).thenReturn(Single.just(list))

        val resultList = mutableListOf<Recipe>()
        val recipe = Recipe("test", "test", "test")
        resultList.add(recipe)
        repo.getDailyRecipes().test().assertValue(resultList)
    }
}