package saverecipes.thomasmacquart.com.recipeme.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import saverecipes.thomasmacquart.com.recipeme.core.AppDatabase
import org.junit.Rule
import org.junit.Test
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe


@RunWith(AndroidJUnit4::class)
class RecipeDaoImplTest {
    private lateinit var  mDatabase : AppDatabase

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().context,
                AppDatabase::class.java)
                        // allowing main thread queries, just for testing
                        .allowMainThreadQueries()
                        .build()
    }


    @Test
    fun insertAndGetUserById() {
        // Given that we have a recipe in the data source
        mDatabase.RecipeDao().saveRecipe(Recipe("test", "desciption", "dessert"))
        // When subscribing to the emissions of user
        mDatabase.RecipeDao()
                .getRecipes()
                .test()
                .assertValue { it.get(0).name == "test" }
    }

    @After
    fun closeDb()  {
        mDatabase.close();
    }
}