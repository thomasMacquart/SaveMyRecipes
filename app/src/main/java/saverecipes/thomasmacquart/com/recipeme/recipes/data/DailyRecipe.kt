package saverecipes.thomasmacquart.com.recipeme.recipes.data

import com.google.gson.annotations.SerializedName

data class DailyRecipe(@SerializedName("title") val title : String,
                       @SerializedName("type") val type: String,
                       @SerializedName("decription") val description : String)
