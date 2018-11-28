package saverecipes.thomasmacquart.com.recipeme.recipes.ui.viewmodel.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import saverecipes.thomasmacquart.com.recipeme.core.AppDatabase
import org.junit.Rule
import org.junit.Test
import saverecipes.thomasmacquart.com.recipeme.recipes.domain.Recipe


@RunWith(AndroidJUnit4::class)
class RecipeDaoImplTest {
    /*private lateinit var  mDatabase : AppDatabase;

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
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
                // assertValue asserts that there was only one emission
                .assertValue(object : Predicate<Recipe>() {
                    @Throws(Exception::class)
                    fun test(recipe: Recipe): Boolean {
                        // The emitted user is the expected one
                        return user.getId().equals(USER.getId()) && user.getUserName().equals(USER.getUserName())
                    }
                })
    }

    @After
    fun closeDb()  {
        mDatabase.close();
    }*/
}