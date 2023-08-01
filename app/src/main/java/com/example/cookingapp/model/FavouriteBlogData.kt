

package com.example.cookingapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.cookingapp.constant.Constant

@Entity(tableName = Constant.FAVOURITE_DATABASEBLOGS)
data class FavouriteBlogData(
    @PrimaryKey(autoGenerate = true)
    var favBlogId:Int,
    @ColumnInfo(name = "favBlogTitle")
    var favBlogTitle:String,
    @ColumnInfo(name = "favBlogDescription")
    var favBlogDescription:String,
    @ColumnInfo("favBlogPlace")
    var favBlogPlace:String,
    @ColumnInfo(name="isFavourite")
    var isFavourite:Boolean=true
)

/*{
    @PrimaryKey(autoGenerate = true)
    var favBlogId:Int = 0
}*/



