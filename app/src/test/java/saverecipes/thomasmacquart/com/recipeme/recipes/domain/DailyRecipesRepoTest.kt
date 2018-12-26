package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import saverecipes.thomasmacquart.com.recipeme.recipes.data.DailyRecipe
import saverecipes.thomasmacquart.com.recipeme.recipes.data.DailyRecipeDao

internal class DailyRecipesRepoTest {

    @Mock
    lateinit var dao : DailyRecipeDao

    lateinit var repo : DailyRecipesRepo

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        repo = DailyRecipesRepo(dao)
    }

    @Test
    fun `given dao return a list with recipes`() {
        val list = mutableListOf<DailyRecipe>()
        val dailyRecipe = DailyRecipe("test", "test", "test")
        list.add(dailyRecipe)

        Mockito.`when`(dao.getDailyRecipes()).thenReturn(Single.just(list))

        val resultList = mutableListOf<Recipe>()
        val recipe = Recipe("test", "test", "test")
        resultList.add(recipe)
        repo.getDailyRecipes().test().assertValue(resultList)
    }
}