package saverecipes.thomasmacquart.com.recipeme.recipes.model


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RecipeDetailsUiModelTest {


    @Test
    fun getTitle() {
        val uiModel = RecipeDetailsUiModel("test", "test", "test")
        assertEquals("test", uiModel.title )
    }

    @Test
    fun getType() {
        val uiModel = RecipeDetailsUiModel("test", "test", "test")
        assertEquals("test", uiModel.type )

    }

    @Test
    fun getDescription() {
        val uiModel = RecipeDetailsUiModel("test", "test", "test")
        assertEquals("test", uiModel.description )
    }
}