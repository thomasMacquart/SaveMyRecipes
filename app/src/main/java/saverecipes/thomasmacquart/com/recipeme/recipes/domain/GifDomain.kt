package saverecipes.thomasmacquart.com.recipeme.recipes.domain

data class GifDomain(val data: Data)

data class  Data(val gifList : List<Giphy>)

data class Giphy(val embed_url : String)