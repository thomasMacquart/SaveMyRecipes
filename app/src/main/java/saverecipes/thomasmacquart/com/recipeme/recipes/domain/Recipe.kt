package saverecipes.thomasmacquart.com.recipeme.recipes.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by thomas.macquart on 28/10/2017.
 */

@Entity(tableName = "Recipe")
data class Recipe (

        @ColumnInfo(name = "NAME") var name : String = "",
        @ColumnInfo(name = "DESCRIPTION") var description : String = "",
        @ColumnInfo(name = "TYPE") var type : String = ""
) {
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true) var id: Long = 0
}