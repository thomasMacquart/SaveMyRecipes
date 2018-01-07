package saverecipes.thomasmacquart.com.recipeme

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by thomas.macquart on 28/10/2017.
 */

@Entity(tableName = "Recipe")
data class Recipe (
        @PrimaryKey(autoGenerate = true)
        val uid: Long,
        @ColumnInfo(name = "NAME") val name : String = "",
        @ColumnInfo(name = "DESCRIPTION") val description : String = ""
)